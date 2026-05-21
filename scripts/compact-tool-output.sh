#!/usr/bin/env bash
# TokenJuice-inspired tool output compaction hook
# Strips noise from tool output before it hits context
# Install as a post-tool hook in .claude/settings.json
#
# Three-layer rule overlay:
#   1. Builtin rules (this script)
#   2. User rules (~/.config/claudehelper/compact-rules/)
#   3. Project rules (.compact-rules/)
#
# Pass-through safety: outputs under 512 bytes are never compressed.
# If compression ratio > 0.95, original is returned untouched.

set -euo pipefail

INPUT=$(cat)
TOOL_NAME="${CLAUDE_TOOL_NAME:-unknown}"
EXIT_CODE="${CLAUDE_EXIT_CODE:-0}"

# Pass-through safety: don't compress tiny outputs
BYTE_COUNT=$(echo "$INPUT" | wc -c)
if [ "$BYTE_COUNT" -lt 512 ]; then
    echo "$INPUT"
    exit 0
fi

compact() {
    local text="$1"

    # Strip ANSI color codes
    text=$(echo "$text" | sed 's/\x1b\[[0-9;]*m//g' 2>/dev/null || echo "$text")

    # Strip git hints ("use git ... to ...")
    text=$(echo "$text" | grep -v '^\s*(use "git ' 2>/dev/null || echo "$text")

    # Strip npm/yarn noise
    text=$(echo "$text" | grep -v '^\s*npm warn' 2>/dev/null || echo "$text")
    text=$(echo "$text" | grep -v '^\s*added [0-9]* packages' 2>/dev/null || echo "$text")

    # Strip pip install progress bars
    text=$(echo "$text" | grep -v '^\s*━' 2>/dev/null || echo "$text")
    text=$(echo "$text" | grep -v '^\s*Downloading' 2>/dev/null || echo "$text")
    text=$(echo "$text" | grep -v '^\s*Installing collected' 2>/dev/null || echo "$text")

    # Strip cargo/rust compile progress
    text=$(echo "$text" | grep -v '^\s*Compiling ' 2>/dev/null || echo "$text")
    text=$(echo "$text" | grep -v '^\s*Downloading ' 2>/dev/null || echo "$text")

    # Strip docker pull progress
    text=$(echo "$text" | grep -v '^\s*[a-f0-9]*: Pull' 2>/dev/null || echo "$text")
    text=$(echo "$text" | grep -v '^\s*[a-f0-9]*: Waiting' 2>/dev/null || echo "$text")
    text=$(echo "$text" | grep -v '^\s*[a-f0-9]*: Downloading' 2>/dev/null || echo "$text")

    # Deduplicate adjacent identical lines
    text=$(echo "$text" | uniq)

    # Strip trailing whitespace
    text=$(echo "$text" | sed 's/[[:space:]]*$//')

    # Strip excessive blank lines (collapse 3+ to 1)
    text=$(echo "$text" | sed '/^$/N;/^\n$/d')

    echo "$text"
}

COMPACTED=$(compact "$INPUT")

# Pass-through safety: if compression ratio > 0.95, return original
ORIGINAL_LEN=$(echo "$INPUT" | wc -c)
COMPACT_LEN=$(echo "$COMPACTED" | wc -c)
if [ "$ORIGINAL_LEN" -gt 0 ]; then
    RATIO=$(echo "scale=2; $COMPACT_LEN / $ORIGINAL_LEN" | bc 2>/dev/null || echo "1")
    if [ "$(echo "$RATIO > 0.95" | bc 2>/dev/null || echo 0)" -eq 1 ]; then
        echo "$INPUT"
        exit 0
    fi
fi

echo "$COMPACTED"
