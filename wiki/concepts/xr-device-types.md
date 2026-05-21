---
title: XR Device Types
type: concept
tldr: "Headsets vs wired vs display glasses"
sources:
  - raw/docs/jetpack-xr-overview.md
  - raw/docs/catalyst-program.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[compose-for-xr]]"
  - "[[compose-glimmer]]"
  - "[[jetpack-projected]]"
  - "[[headsets-vs-glasses]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [device-types, xr-devices]
---

# XR Device Types

The Jetpack XR SDK targets three device categories with different capabilities and SDKs.

## Device Matrix

| Feature | XR Headsets | Wired XR Glasses | Audio/Display Glasses |
|---------|-------------|-------------------|----------------------|
| Display | Immersive (opaque) | Spatial (see-through) | Overlay (see-through) |
| Compute | Standalone | Tethered to phone/PC | Phone-powered |
| Input | Controllers, hands, gaze | Hands, gaze | Voice, tap, swipe |
| 3D content | Full scene graph | Full scene graph | 2D panels only |
| SDK | Compose for XR, SceneCore | Compose for XR, SceneCore | Glimmer, Projected |
| ARCore | Yes | Yes | Limited |
| Game engines | N/A | Unity, Godot, Unreal | N/A |

## Catalyst Hardware

The [[catalyst-program]] provides dev kits for:
- **Wired XR glasses** — XREAL Project Aura
- **Audio glasses** — TBA
- **Display glasses** — TBA

## Choosing a Target

- **Headsets**: Richest experience, full immersion, but niche market
- **Wired glasses**: Middle ground — spatial computing with real-world visibility
- **Display glasses**: Widest potential audience, but most constrained UI
