#!/usr/bin/env bash
#
# find-contradictions.sh — Identify candidate contradictions in the wiki
#
# This script does the mechanical work (finding overlapping pages and extracting
# claims) so the LLM only needs to do the judgment work (comparing claims).
#
# Usage:
#   ./scripts/find-contradictions.sh              # Full scan, human-readable
#   ./scripts/find-contradictions.sh --pairs-only  # Just list candidate page pairs
#   ./scripts/find-contradictions.sh --claims       # Extract claims from all pages
#   ./scripts/find-contradictions.sh --audit        # List existing contradictions
#
# Contradiction patterns learned from manual review:
#   1. STALE CLAIMS — A fact was true when written but the product changed
#      (e.g., "auto mode is SDK-only" → it launched for CLI months later)
#   2. COST CONFUSION — Two mechanisms in the same config file work differently
#      (e.g., hooks don't re-inject but rules do, despite both being in settings.json)
#   3. FALSE IMPLICATION — Statement A is true, but readers infer B which is false
#      (e.g., "prompt-cached" implies length doesn't matter → it does for adherence)
#   4. PRECEDENCE AMBIGUITY — Two features both claim authority over the same decision
#      (e.g., hooks say "cannot be ignored" but sandbox says "auto-approves")
#   5. HIDDEN COST — Feature X is recommended as better than Y, but X has costs not mentioned
#      (e.g., "move to skills" but skill descriptions aren't free)
#
set -uo pipefail

WIKI_DIR="wiki"
MODE="full"

while [[ $# -gt 0 ]]; do
  case "$1" in
    --pairs-only) MODE="pairs"; shift ;;
    --claims) MODE="claims"; shift ;;
    --audit) MODE="audit"; shift ;;
    *) echo "Unknown flag: $1"; exit 1 ;;
  esac
done

cd "$(git rev-parse --show-toplevel 2>/dev/null || pwd)"

# ═══════════════════════════════════════════════════════════════════════
#  AUDIT: List existing contradictions
# ═══════════════════════════════════════════════════════════════════════

audit_contradictions() {
  echo "=== EXISTING CONTRADICTIONS ==="
  echo ""
  local count=0
  for f in $(grep -rl "\[!contradiction\]" "$WIKI_DIR"/ 2>/dev/null); do
    count=$((count + 1))
    local page=$(basename "$f" .md)
    local resolution=$(grep "^> Resolution:" "$f" 2>/dev/null | head -1 | sed 's/> Resolution: *//')
    echo "  $count. $page"
    echo "     Resolution: ${resolution:-pending}"
    echo ""
  done
  if [[ $count -eq 0 ]]; then
    echo "  No contradictions found."
  else
    echo "  Total: $count contradiction(s)"
  fi
}

# ═══════════════════════════════════════════════════════════════════════
#  PAIRS: Find pages that share 2+ related links (overlap candidates)
# ═══════════════════════════════════════════════════════════════════════

find_pairs() {
  echo "=== CANDIDATE PAGE PAIRS (share 2+ related links) ==="
  echo ""

  # Build a map: page → set of related links
  declare -A PAGE_LINKS
  for f in $(find "$WIKI_DIR" -name "*.md" ! -name "index.md" ! -name "log.md" ! -name "hot.md" ! -name "overview.md"); do
    local slug=$(basename "$f" .md)
    local links=$(grep -oh '\[\[[a-z0-9-]*\]\]' "$f" 2>/dev/null | sed 's/\[\[//;s/\]\]//' | sort -u | tr '\n' ',' | sed 's/,$//')
    PAGE_LINKS["$slug"]="$links"
  done

  # Compare all pairs
  local slugs=(${!PAGE_LINKS[@]})
  local pair_count=0
  for ((i=0; i<${#slugs[@]}; i++)); do
    for ((j=i+1; j<${#slugs[@]}; j++)); do
      local a="${slugs[$i]}"
      local b="${slugs[$j]}"
      local links_a="${PAGE_LINKS[$a]}"
      local links_b="${PAGE_LINKS[$b]}"

      # Count shared links
      local shared=0
      IFS=',' read -ra ARR_A <<< "$links_a"
      for link in "${ARR_A[@]}"; do
        [[ -z "$link" ]] && continue
        if [[ ",$links_b," == *",$link,"* ]]; then
          shared=$((shared + 1))
        fi
      done

      if [[ $shared -ge 2 ]]; then
        pair_count=$((pair_count + 1))
        echo "  $a ↔ $b ($shared shared links)"
      fi
    done
  done
  echo ""
  echo "  Total candidate pairs: $pair_count"
}

# ═══════════════════════════════════════════════════════════════════════
#  CLAIMS: Extract assertive statements from pages
# ═══════════════════════════════════════════════════════════════════════

extract_claims() {
  echo "=== EXTRACTABLE CLAIMS ==="
  echo ""
  echo "Pages with numbers, absolutes, or recommendations that could conflict:"
  echo ""

  for f in $(find "$WIKI_DIR" -name "*.md" ! -name "index.md" ! -name "log.md" ! -name "hot.md" ! -name "overview.md"); do
    local slug=$(basename "$f" .md)
    local claims=""

    # Find lines with specific numbers
    local numbers=$(grep -n "[0-9]\+%" "$f" 2>/dev/null | grep -iv "^[0-9]*:---\|^[0-9]*:#\|^[0-9]*:tldr\|^[0-9]*:created\|^[0-9]*:updated\|^[0-9]*:last_verified" | head -5)

    # Find absolute statements (always, never, must, cannot)
    local absolutes=$(grep -in "\balways\b\|\bnever\b\|\bmust\b\|\bcannot\b\|\bonly\b" "$f" 2>/dev/null | grep -iv "^[0-9]*:---\|^[0-9]*:#\|^[0-9]*:tldr\|^[0-9]*:> \[!contradiction\]\|^[0-9]*:> " | head -5)

    # Find recommendations (should, recommended, best practice, avoid)
    local recs=$(grep -in "\bshould\b\|\brecommended\b\|\bbest practice\b\|\bavoid\b\|\bprefer\b" "$f" 2>/dev/null | grep -iv "^[0-9]*:---\|^[0-9]*:#\|^[0-9]*:tldr\|^[0-9]*:> " | head -3)

    if [[ -n "$numbers" || -n "$absolutes" || -n "$recs" ]]; then
      echo "  ── $slug ──"
      [[ -n "$numbers" ]] && echo "$numbers" | sed 's/^/    NUM: /'
      [[ -n "$absolutes" ]] && echo "$absolutes" | sed 's/^/    ABS: /'
      [[ -n "$recs" ]] && echo "$recs" | sed 's/^/    REC: /'
      echo ""
    fi
  done
}

# ═══════════════════════════════════════════════════════════════════════
#  FULL: Complete analysis
# ═══════════════════════════════════════════════════════════════════════

full_scan() {
  echo "============================================"
  echo "  CONTRADICTION SCAN — $(date +%Y-%m-%d)"
  echo "============================================"
  echo ""

  audit_contradictions
  echo ""
  echo "─────────────────────────────────────────────"
  echo ""
  find_pairs
  echo ""
  echo "─────────────────────────────────────────────"
  echo ""
  extract_claims
  echo ""
  echo "─────────────────────────────────────────────"
  echo ""
  echo "=== CONTRADICTION PATTERNS TO CHECK ==="
  echo ""
  echo "  When reviewing candidate pairs, check for:"
  echo ""
  echo "  1. STALE CLAIMS — Was this true when written but the product changed?"
  echo "     Signal: dates in frontmatter don't match, whats-new-2026 has newer info"
  echo ""
  echo "  2. COST CONFUSION — Do two mechanisms in the same system work differently?"
  echo "     Signal: both pages reference the same config file but describe different behavior"
  echo ""
  echo "  3. FALSE IMPLICATION — Is statement A true but readers would wrongly infer B?"
  echo "     Signal: a claim uses words like 'efficient' or 'free' without quantifying"
  echo ""
  echo "  4. PRECEDENCE AMBIGUITY — Do two features both claim to control the same decision?"
  echo "     Signal: both pages say 'this takes priority' or 'cannot be overridden'"
  echo ""
  echo "  5. HIDDEN COST — Is feature X recommended over Y without mentioning X's downsides?"
  echo "     Signal: one page recommends something that another page warns about"
  echo ""
  echo "  Record findings with:"
  echo "  > [!contradiction]"
  echo "  > [[page-a]] says X. [[page-b]] says Y."
  echo "  > Last reviewed: $(date +%Y-%m-%d)"
  echo "  > Resolution: pending"
  echo ""
  echo "============================================"
}

# ── MAIN ────────────────────────────────────────────────────────────
case "$MODE" in
  pairs) find_pairs ;;
  claims) extract_claims ;;
  audit) audit_contradictions ;;
  full) full_scan ;;
esac
