---
title: Jetpack XR SDK
type: concept
tldr: "Core SDK for Android XR apps"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[compose-for-xr]]"
  - "[[scenecore]]"
  - "[[arcore-xr]]"
  - "[[compose-glimmer]]"
  - "[[jetpack-projected]]"
  - "[[xr-device-types]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [jetpack-xr, xr-sdk]
---

# Jetpack XR SDK

The Jetpack XR SDK is Google's toolkit for building immersive and augmented experiences on Android XR devices. It includes tools, libraries, and APIs for spatial UI, 3D content, and perception.

## Core Libraries

| Library | Purpose | Devices |
|---------|---------|---------|
| [[compose-for-xr]] | Declarative spatial UI | Headsets, wired glasses |
| Material Design for XR | Adaptive spatial components | Headsets, wired glasses |
| [[scenecore]] | 3D scene graph, entities, environments | Headsets, wired glasses |
| [[arcore-xr]] | Perception: planes, hands, depth, anchors | All XR devices |
| [[compose-glimmer]] | Glasses-specific UI toolkit | Display glasses |
| [[jetpack-projected]] | Phone-to-glasses communication | Audio/display glasses |

## Requirements

- **minSdk:** 24
- **compileSdk:** 34+
- **Android Studio:** Latest Canary build (for XR tools)
- **ProGuard note:** Must add `compileOnly "com.android.extensions.xr:extensions-xr:1.1.0"` — using `implementation` breaks runtime

## Development Paths

**Immersive (headsets/wired glasses):**
Spatial UI → 3D models → environments → perception → spatial audio/video

**Augmented (audio/display glasses):**
Glasses activity → Glimmer UI → voice/ASR → Gemini Live → hardware access

## Current Status

Developer Preview 4 (as of May 2026). Libraries under active development with known issues tracked in release notes.
