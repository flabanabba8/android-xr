---
title: "Source: XR Quality Guidelines"
type: source-summary
tldr: "App quality tiers + performance targets"
sources:
  - raw/docs/xr-quality-guidelines.md
related:
  - "[[google-play-xr]]"
  - "[[catalyst-program]]"
  - "[[jetpack-xr-sdk]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Source: Android XR App Quality Guidelines

**Source:** developer.android.com/docs/quality-guidelines/android-xr
**Fetched:** 2026-05-21

## Key Takeaways

1. **Three compatibility tiers**: mobile (auto-compatible), large screen (1024×720dp panel), differentiated (≥1 XR feature)
2. **Performance targets**: <11.1ms frame time (90Hz), ≥1856×2160 per eye, cold start <2s, crash ~1%
3. **Input**: minimum 48×48dp targets (56dp recommended), hand input as baseline, physics-based scrolling
4. **Safety**: no strobing >3/sec, no abrupt camera moves, consistent reference frame
5. **Visual**: separate panels for controls, safe environment tonal range, back navigation required in manifest
6. **Publishing**: mobile track (auto-discoverable) or dedicated XR track, 12 testers for 14 days before production
