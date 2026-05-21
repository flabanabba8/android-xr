---
title: "Source: XR Blocks"
type: source-summary
tldr: "XR Blocks README + full manual"
sources:
  - raw/docs/xrblocks-readme.md
  - raw/docs/xrblocks-manual.md
related:
  - "[[xrblocks]]"
  - "[[xrblocks-architecture]]"
  - "[[xrblocks-gestures]]"
  - "[[xrblocks-depth-physics]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Source: XR Blocks

**Sources:** github.com/google/xrblocks README + xrblocks.github.io/docs/ manual (12 pages)
**Fetched:** 2026-05-21

## Key Takeaways

1. **WebXR-first**: Built on three.js, targets Chrome v136+ on Android XR. Not a native SDK — runs in browser.
2. **Script lifecycle**: Unity-like pattern (init/update) on THREE.Object3D entities. Not ECS.
3. **6 built-in gestures**: pinch, open-palm, fist, thumbs-up, point, spread. Configurable thresholds, custom TF Lite/PyTorch models supported.
4. **Depth pipeline**: 40x40 or 160x160 mesh, GPU depth texture, occlusion system. Per-object resume/pause.
5. **Rapier physics**: 45fps fixed update, standard rigid body + collider pattern.
6. **Modular deps**: Physics, Gemini AI, OpenAI, UI (Lit), 3D text (Troika), Gaussian splatting — all optional, auto-detected.
7. **Desktop simulator**: WASD + mouse, depth/hands sim, does NOT emulate WebXR APIs. Import SimulatorAddons.js.
8. **UI system**: View-based with relative positioning. Panel, SpatialPanel, GridView, ImageView, TextView, Pagers.
9. **Model Viewer**: GLTF/GLB with DragManager for move/rotate/scale. Draco + KTX2 compression.
10. **Gem**: Rapid prototyping tool at xrblocks.github.io/gem — "vibe coding XR" with Gemini.
