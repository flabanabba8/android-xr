---
title: "Headsets vs Wired Glasses vs Display Glasses"
type: comparison
tldr: "Capabilities by device type"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[xr-device-types]]"
  - "[[jetpack-xr-sdk]]"
  - "[[compose-for-xr]]"
  - "[[compose-glimmer]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Headsets vs Wired Glasses vs Display Glasses

## Capability Comparison

| Capability | XR Headsets | Wired XR Glasses | Display Glasses |
|-----------|-------------|-------------------|-----------------|
| **Display** | Immersive (opaque) | Spatial (see-through) | Overlay (see-through) |
| **Compute** | Standalone | Tethered | Phone-powered |
| **3D scene graph** | Yes | Yes | No |
| **Compose for XR** | Yes | Yes | No |
| **Compose Glimmer** | No | No | Yes |
| **SceneCore** | Yes | Yes | No |
| **ARCore** | Full | Full | Limited |
| **Spatial audio** | Yes | Yes | Basic |
| **Game engines** | N/A | Unity, Godot, Unreal | N/A |
| **Input** | Controllers, hands, gaze | Hands, gaze | Voice, tap, swipe |
| **Projected API** | No | No | Yes |

## When to Choose What

- **Headset**: Maximum immersion, richest SDK surface, but smallest install base
- **Wired glasses**: Spatial computing with real-world visibility, game engine support
- **Display glasses**: Widest potential audience, lightest hardware, most constrained UI
