---
title: Jetpack Compose for XR
type: concept
tldr: "Declarative spatial UI for XR"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[spatial-ui]]"
  - "[[scenecore]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [compose-xr, jetpack-compose-xr]
---

# Jetpack Compose for XR

Declarative spatial UI using familiar Compose patterns, extended for 3D. Available for XR headsets and wired XR glasses.

## Key Components

- **SpatialPanel** — 2D panels positioned in 3D space
- **Orbiter** — UI elements that orbit around panels or entities
- **SceneCoreEntity** — Composable bridge to place 3D models relative to UI
- **Subspace modifiers** — Position, rotate, scale composables in 3D

```kotlin
SceneCoreEntity(
    entity = my3DModel,
    modifier = subspaceModifier,
)
```

## What It Bridges

Compose for XR sits between standard 2D Compose/Views and the 3D scene graph ([[scenecore]]). You can spatialize existing Android apps or build fully spatial layouts from scratch.

## Design Patterns

- Use `SpatialPanel` for content surfaces
- Use `Orbiter` for controls/toolbars
- Material Design for XR provides adaptive components optimized for spatial layouts
- Subspace modifiers handle positioning without manual matrix math
