---
title: Meta Quest OpenXR
type: entity
tldr: "Quest dev via OpenXR native SDK"
sources:
  - raw/docs/meta-quest-openxr.md
related:
  - "[[xr-device-types]]"
  - "[[headsets-vs-glasses]]"
  - "[[live-captioning-architecture]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [meta-quest, quest-openxr, oculus-openxr]
---

# Meta Quest OpenXR

Native XR development for Meta Quest devices (Quest 1, 2, 3, Pro) via OpenXR 1.0.

## SDK Setup

```gradle
buildFeatures { prefab true }
dependencies {
    implementation 'org.khronos.openxr:openxr_loader_for_android:1.0.34'
}
```

GitHub: https://github.com/meta-quest/Meta-OpenXR-SDK (v85.0, Feb 2026)

## Key Features

| Feature | Details |
|---------|---------|
| Hand tracking | Skinned mesh, collision capsules, pinch UI |
| Passthrough | Basic, masked, projected + style options |
| Spatial anchors | Handle, maintain, share |
| Eye tracking | Gaze data |
| Face tracking | Blendshape weights |
| Body tracking | Skeleton joints |
| Scene understanding | Floor, wall, furniture |
| Microgestures | Thumb swipe, tap |

## For Live Captioning

Quest provides passthrough mode for see-through captioning. Use OpenXR passthrough extensions + spatial text panels to overlay captions on the real world. Hand tracking for UI interaction (font size, language selection).

## Cross-Platform Note

Android XR (Jetpack XR SDK) and Meta Quest (OpenXR) are separate ecosystems. For maximum reach, consider an abstraction layer or separate builds targeting each platform.
