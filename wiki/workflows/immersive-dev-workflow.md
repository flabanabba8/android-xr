---
title: Immersive Development Workflow
type: workflow
tldr: "Build path for headsets/wired glasses"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[compose-for-xr]]"
  - "[[scenecore]]"
  - "[[arcore-xr]]"
  - "[[spatial-ui]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Immersive Development Workflow

Build path for XR headsets and wired XR glasses.

## Development Progression

```
1. Bring existing Android app to 3D (spatialize)
    ↓
2. Develop spatial UI (Compose for XR)
    ↓
3. Add 3D models & environments (SceneCore)
    ↓
4. Apply Material Design for XR
    ↓
5. Add spatial audio/video
    ↓
6. Integrate ARCore perception (optional)
    ↓
7. Run on emulator / physical device
    ↓
8. Debug with Layout Inspector
```

## Key Libraries at Each Stage

| Stage | Library |
|-------|---------|
| Spatial UI | [[compose-for-xr]], Material Design for XR |
| 3D content | [[scenecore]] |
| Perception | [[arcore-xr]] |
| Audio | Spatial audio API (via SceneCore) |
| Testing | XR emulator, Layout Inspector |
