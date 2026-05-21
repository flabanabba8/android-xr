# Android XR App Quality Guidelines

Source: https://developer.android.com/docs/quality-guidelines/android-xr
Fetched: 2026-05-21

## Tiers
1. Compatible Mobile App — existing app, auto-compatible, no XR modifications
2. Compatible Large Screen App — large screen Tier 1/2, 1024×720dp panel
3. Differentiated App — explicitly designed for XR, ≥1 XR feature required

## Differentiated Requirements

### Performance
- Rendering: <11.1ms (90Hz) or <13.8ms (72Hz)
- Resolution: ≥1856×2160 per eye
- Cold start: <2s, Warm start: <1s
- ANRs: <1 in 99.5% sessions, Crash: ~1%

### Input
- Min target size: 48×48dp, recommended 56×56dp
- Must support hand input as baseline (no controller required)
- Hand raycast + gestural support

### Safety
- Strobing: <3 flashes/sec, provide disable option
- Motion sickness: no abrupt camera moves, consistent reference frame

### Visual
- ≥1 XR feature: spatial panels, environments, 3D models, spatial audio
- Environments: safe tonal range, no brightness spikes
- Scroll: physics/momentum on carousels and lists
- Menus/controls: separate panel or orbiter, not in main content

### Back navigation
Manifest: enableOnBackInvokedCallback="True"
