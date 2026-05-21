---
title: "Source: Meta Quest OpenXR"
type: source-summary
tldr: "Quest native SDK features + setup"
sources:
  - raw/docs/meta-quest-openxr.md
related:
  - "[[meta-quest-openxr]]"
  - "[[headsets-vs-glasses]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Source: Meta Quest OpenXR SDK

**Source:** developers.meta.com/horizon/documentation/native/android/mobile-openxr/
**Fetched:** 2026-05-21

## Key Takeaways

1. Quest 1/2/3/Pro supported, OpenXR 1.0, OS v62+
2. SDK v85.0 (Feb 2026), GitHub: meta-quest/Meta-OpenXR-SDK
3. Rich feature set: hand tracking (skinned mesh, collision capsules, pinch UI), passthrough (basic/masked/projected), spatial anchors, eye/face/body tracking, scene understanding, microgestures (thumb swipe/tap)
4. Passthrough modes enable see-through captioning overlay on real world
5. Separate ecosystem from Android XR — cross-platform requires abstraction layer or separate builds
6. Gradle: `buildFeatures { prefab true }`, dep: `org.khronos.openxr:openxr_loader_for_android:1.0.34`
7. Required extensions: XR_KHR_loader_init, XR_KHR_android_create_instance
8. Manifest: `org.khronos.openxr.intent.category.IMMERSIVE_HMD` intent filter
