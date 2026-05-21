---
title: Jetpack SceneCore
type: concept
tldr: "3D scene graph and entity system"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[compose-for-xr]]"
  - "[[spatial-ui]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [scenecore, scene-core]
---

# Jetpack SceneCore

Scene graph manipulation for 3D content in XR. Available for headsets and wired XR glasses.

## Capabilities

- Place and arrange 3D entities in space
- Set spatial environments (skyboxes, lighting)
- Create `PanelEntity` instances (2D content in 3D space)
- Add spatial audio sources positioned in the scene
- Make entities movable, resizable, and anchorable by users
- Views-based UI spatializing (for non-Compose apps)

## Entity Types

- **GltfModelEntity** — 3D models loaded from glTF/glb files
- **PanelEntity** — 2D UI surfaces positioned in 3D
- **SpatialAudioEntity** — Positional audio sources
- **EnvironmentEntity** — Skyboxes and environmental settings

## Relationship to Compose

[[compose-for-xr]] provides the declarative layer on top of SceneCore. You can use SceneCore directly for lower-level control, or use Compose composables that wrap SceneCore entities.
