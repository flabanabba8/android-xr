---
title: "Source: Compose for XR Spatial UI"
type: source-summary
tldr: "SpatialPanel, Orbiter, layout APIs"
sources:
  - raw/docs/compose-xr-spatial-ui.md
related:
  - "[[spatial-ui]]"
  - "[[compose-for-xr]]"
  - "[[scenecore]]"
  - "[[live-captioning-architecture]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Source: Compose for XR Spatial UI

**Source:** developer.android.com/develop/xr/jetpack-xr-sdk/ui-compose
**Fetched:** 2026-05-21

## Key Takeaways

1. **SpatialPanel**: 2D Compose content in 3D space. Must be inside Subspace. SubspaceModifier for size/position.
2. **Orbiter**: attached controls that anchor to panels (Bottom, Top, Left, Right). Reusable in 2D fallback.
3. **Spatial layouts**: SpatialRow, SpatialColumn, SpatialBox, SpatialSpacer. 825dp curve radius for multi-panel rows.
4. **3D models**: rememberSpatialGltfModelState + SpatialGltfModel composable. Supports path, URI, raw data.
5. **Hybrid components**: SpatialDialog (125dp pushback), SpatialPopup (z-depth), SpatialElevation. All fallback to 2D.
6. **User interaction**: .transformingMovable(), .resizable(), .movable(shouldScaleWithDistance).
7. **For captioning**: SpatialPanel with large text, head-tracked or world-anchored, configurable sizing.
