---
title: Spatial UI
type: concept
tldr: "Panels, orbiters, 3D layouts"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[compose-for-xr]]"
  - "[[scenecore]]"
  - "[[jetpack-xr-sdk]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [spatial-layout, spatial-panels]
---

# Spatial UI

The spatial UI system lets you arrange 2D and 3D content in three-dimensional space.

## Core Primitives

| Primitive | What it does |
|-----------|-------------|
| **SpatialPanel** | A 2D surface (Compose or Views) placed in 3D space |
| **Orbiter** | A UI element that orbits around a panel or entity (toolbars, menus) |
| **Subspace modifiers** | Position, rotate, scale composables in 3D without matrix math |
| **PanelEntity** | SceneCore-level panel (lower-level than SpatialPanel) |

## Layout Patterns

- **Single-panel apps** — one main content surface, orbiters for controls
- **Multi-panel layouts** — multiple panels arranged spatially (dashboard, comparison view)
- **Mixed 2D+3D** — panels alongside 3D models in the scene
- **Passthrough** — content overlaid on the real world via ARCore

## From 2D to Spatial

Existing Android apps can be spatialized incrementally:
1. Start with your existing 2D app running in a flat panel
2. Add `SpatialPanel` to lift content into 3D
3. Add `Orbiter` for floating controls
4. Optionally add 3D models via [[scenecore]]
