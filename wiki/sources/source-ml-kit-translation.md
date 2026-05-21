---
title: "Source: ML Kit Translation"
type: source-summary
tldr: "On-device translation API reference"
sources:
  - raw/docs/ml-kit-translation.md
related:
  - "[[ml-kit-translation]]"
  - "[[live-captioning-architecture]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Source: ML Kit On-Device Translation

**Source:** developers.google.com/ml-kit/language/translation/android
**Fetched:** 2026-05-21

## Key Takeaways

1. **50+ languages**, same models as Google Translate offline mode
2. **~30MB per language model**, downloaded on demand
3. **API 23+** minimum, fully on-device (no server calls)
4. Must call `downloadModelIfNeeded()` before `translate()`
5. Use `RemoteModelManager` to pre-download, list, or delete models
6. Combine with Language Identification API for auto-detection
7. Cleanup via `close()` or LifecycleObserver
