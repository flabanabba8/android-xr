---
title: ARCore for Jetpack XR
type: concept
tldr: "Perception: planes, hands, depth, anchors"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[scenecore]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [arcore-xr, arcore-jetpack-xr]
---

# ARCore for Jetpack XR

Perception capabilities for all XR device types. OpenXR-based for cross-device compatibility.

## Features

| Feature | Description |
|---------|-------------|
| Plane detection | Detect floors, walls, tabletops with semantic labels |
| Persistent anchors | Place content that stays fixed in the real world |
| Hand tracking | Track hand positions and gestures |
| Face tracking | Track facial features and expressions |
| Depth estimation | Understand distance to surfaces |
| Device pose | Track headset/glasses position and orientation |
| Hit testing | Ray-cast from user gaze/pointer into the scene |
| Motion tracking | 6DOF device tracking |

## Semantic Plane Labels

Plane detection includes labels: floor, wall, tabletop, ceiling, door, window. This enables context-aware placement (e.g., mount a virtual screen on a wall).
