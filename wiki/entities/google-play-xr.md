---
title: Google Play for XR
type: entity
tldr: "XR app publishing and quality"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[catalyst-program]]"
  - "[[jetpack-xr-sdk]]"
created: 2026-05-21
updated: 2026-05-21
confidence: medium
last_verified: 2026-05-21
aliases: [google-play-xr, xr-distribution]
---

# Google Play for XR

Android XR apps are distributed through Google Play with XR-specific quality guidelines.

## Compatibility Tiers

| Tier | What | Requirements |
|------|------|-------------|
| **Compatible Mobile** | Existing app, no XR mods | Auto-compatible, runs in flat panel |
| **Compatible Large Screen** | Large screen Tier 1/2 app | 1024×720dp panel, auto-opted in |
| **Differentiated** | Built for XR | ≥1 XR feature (spatial panel, 3D, environments) |

## Quality Requirements (Differentiated Apps)

### Performance
- Rendering: <11.1ms (90Hz) or <13.8ms (72Hz)
- Resolution: ≥1856×2160 per eye
- Cold start: <2s mean, warm start: <1s mean
- Crash rate: ~1% or lower, ANRs: <1 in 99.5% sessions

### Input
- Minimum target size: 48×48dp (recommended 56×56dp)
- Must support hand input as baseline (controllers optional)
- Physics-based scrolling on carousels and lists

### Safety & Comfort
- No strobing >3 flashes/sec without disable option
- No abrupt camera moves (motion sickness prevention)
- Consistent frame of reference

### Visual
- Menus/controls in separate panel or orbiter (not main content)
- Environments: safe tonal range, no brightness spikes

## Release Tracks

- **Mobile release track** — existing apps auto-discoverable on XR
- **Android XR release track** — dedicated track for XR-first apps

## Manifest

```xml
enableOnBackInvokedCallback="True"
```

Required for proper back navigation in SpatialPanel.

## Catalyst Milestone

Publishing on Google Play is the final milestone for [[catalyst-program]] grant distribution. Must have 12 active testers for 14 consecutive days before production access.
