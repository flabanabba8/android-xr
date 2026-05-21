---
title: "Source: ML Kit Language ID"
type: source-summary
tldr: "100+ language detection, on-device"
sources:
  - raw/docs/ml-kit-language-id.md
related:
  - "[[ml-kit-translation]]"
  - "[[speech-recognition]]"
  - "[[live-captioning-architecture]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Source: ML Kit Language Identification

**Source:** developers.google.com/ml-kit/language/identification/android
**Fetched:** 2026-05-21

## Key Takeaways

1. **100+ languages** detected, including romanized scripts for Arabic, Chinese, Hindi, Japanese, Russian
2. **Two APIs**: identifyLanguage() (single best) and identifyPossibleLanguages() (list with confidence)
3. **Bundled** (~900KB) or **unbundled** (~200KB via Play Services). API 23+.
4. **Confidence thresholds**: configurable (default 0.5 for single, 0.01 for list). Returns "und" below threshold.
5. **For captioning pipeline**: detect source language → feed to ML Kit Translation → display translated text
6. **Fast, on-device**: no network calls, real-time capable
