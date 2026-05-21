---
name: wiki-lint
description: "Run wiki quality checks: automated script for structural issues, then LLM-powered deep analysis for contradictions, model collapse, and semantic link quality."
user_invocable: true
disable-model-invocation: true
allowed-tools: Bash(./scripts/wiki-lint.sh*) Bash(cat *) Read Grep Glob
---

# Lint the Wiki

## Step 1: Run the Automated Script (Tier 1)

```bash
./scripts/wiki-lint.sh
```

This runs 12 checks in <3 seconds with zero LLM cost:

| # | Check | Severity | Score impact |
|---|-------|----------|-------------|
| 1 | Broken wikilinks | Error | -10/each (structure) |
| 2 | Orphan pages | Error | -5/each (structure) |
| 3 | Index completeness | Error | -5/each (structure) |
| 4 | Required frontmatter | Error | -3/each (structure) |
| 5 | Recommended fields (tldr, last_verified, aliases) | Warning | proportional (metadata) |
| 6 | Thin pages (<100 words) | Warning | -5/each (content) |
| 7 | Low confidence pages | Warning | -3/each (content) |
| 8 | Stale pages (>90 days since last_verified) | Warning | -2/each (freshness) |
| 9 | Content hash verification (raw sources changed) | Warning | -1/each (freshness) |
| 10 | hot.md / overview.md currency (>7 days) | Warning | -5/each (freshness) |
| 11 | Semantic link relevance (spot-check sample) | Info | reported only |
| 12 | Page size distribution | Info | reported only |

**Scoring formula (v2):** Structure 40 + Content 20 + Metadata 20 + Freshness 20 = 100

### Other modes
- `./scripts/wiki-lint.sh --diff` — Compare against previous report, show deltas
- `./scripts/wiki-lint.sh --json` — Machine-readable output
- `./scripts/wiki-lint.sh --ci --threshold 90` — Exit 1 if score < threshold

### CI Integration

As a **git pre-commit hook** (`.git/hooks/pre-commit`):
```bash
#!/bin/bash
./scripts/wiki-lint.sh --ci --threshold 80
```

As a **GitHub Action**:
```yaml
- name: Wiki Lint
  run: ./scripts/wiki-lint.sh --ci --threshold 80
```

## Step 2: Review the Report

Read `outputs/lint-<date>.md` and `outputs/lint-<date>.json`. Summarize for the user.

If the score is 90+, stop here unless the user asks for deep analysis.

## Step 3: Tier 2 — Deep Analysis (LLM Required)

Only if score < 90 or user requests. See `references/TIER2.md` for contradiction detection and model collapse detection methods.
