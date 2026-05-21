# Jetpack XR SDK Overview

Fetched from: https://developer.android.com/develop/xr/jetpack-xr-sdk
Date: 2026-05-21

## Core Libraries

1. **Jetpack Compose for XR** — Declarative spatial UI (headsets, wired glasses)
   - SpatialPanel, Orbiter, SceneCoreEntity, subspace modifiers
2. **Material Design for XR** — Adaptive spatial components (headsets, wired glasses)
3. **Jetpack SceneCore** — 3D scene graph (headsets, wired glasses)
   - Entities, environments, spatial audio, movable/resizable/anchorable
4. **ARCore for Jetpack XR** — Perception (all devices)
   - Planes, anchors, hand/face tracking, depth, device pose, hit testing
5. **Jetpack Compose Glimmer** — Glasses UI toolkit (display glasses only)
   - Text, Icon, Button, TitleChip, Lists, Cards, Surfaces
   - Voice, tap, swipe input; specialized theming for see-through displays
6. **Jetpack Projected** — Phone-to-glasses communication (audio/display glasses)
   - Hardware access, projected contexts, permission helpers

## Requirements

- minSdk: 24, compileSdk: 34+
- Android Studio: latest Canary build
- ProGuard: compileOnly "com.android.extensions.xr:extensions-xr:1.1.0" (NOT implementation)

## Device Types

- XR Headsets: immersive, standalone, controllers/hands/gaze
- Wired XR Glasses: spatial see-through, tethered, Unity/Godot/Unreal support
- Audio/Display Glasses: overlay, phone-powered, voice/tap/swipe input

## Development Paths

Immersive: spatial UI → 3D models → environments → perception → spatial audio/video
Augmented: glasses activity → Glimmer UI → ASR/voice → Gemini Live → hardware access

## Status

Developer Preview 4 (May 2026). Active development with known issues.
