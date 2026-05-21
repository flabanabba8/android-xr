---
title: XR Blocks Architecture
type: concept
tldr: "Scripts, Core, modules, lifecycle"
sources:
  - raw/docs/xrblocks-manual.md
related:
  - "[[xrblocks]]"
  - "[[xrblocks-gestures]]"
  - "[[xrblocks-depth-physics]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# XR Blocks Architecture

## Core Pattern

Everything starts with `xb.init(options)`. The Core singleton (`xb.core`) manages renderer, scene, input, depth, physics, and simulator.

## Script Lifecycle

Scripts extend `xb.Script` (which extends `THREE.Object3D`). Not an ECS — each script is an independent entity.

| Method | When |
|--------|------|
| `init()` | When found by Core. Async supported. |
| `update()` | Every frame |
| `onSelectStart/End/Selecting` | Global controller events |
| `onObjectSelectStart/End` | Object-specific (return true to stop propagation) |
| `onHoverEnter/Exit` | Always propagates upward |
| `initPhysics(physics)` | When physics enabled |
| `physicsStep()` | Fixed interval for transform sync |

## Module System

Optional dependencies auto-detected when imported:

| Module | Package | What it adds |
|--------|---------|-------------|
| Physics | `@dimforge/rapier3d` | Rigid bodies, colliders, gravity |
| Gemini AI | `@google/genai` | Multimodal + live conversational |
| OpenAI | `openai` | Text/chat generation |
| UI | `lit` | HTML overlays, simulator UI |
| 3D Text | `troika-three-text` | SDF text rendering |
| Gaussian Splatting | `@sparkjsdev/spark` | Photorealistic scenes |

## Input System

Three controller types:
1. **WebXR Input Sources** — hand tracking + controllers on Android XR
2. **MouseController** — desktop simulator User Mode
3. **GazeController** — screen center for gaze interaction

Access via `xb.core.input`. Raycast results in `intersectionsForController`.

## Desktop Simulator

Import `'xrblocks/addons/simulator/SimulatorAddons.js'`. Toggle modes with Left Shift:
- **User Mode**: WASD movement, mouse = controller, left click = select
- **Navigation Mode**: WASD movement, left click = rotate camera

Supports depth and hands simulation. Does NOT emulate WebXR APIs.
