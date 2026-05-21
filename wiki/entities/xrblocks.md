---
title: XR Blocks
type: entity
tldr: "JS library for rapid AI+XR prototyping"
sources:
  - raw/docs/xrblocks-readme.md
  - raw/docs/xrblocks-manual.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[xr-device-types]]"
  - "[[xrblocks-architecture]]"
  - "[[xrblocks-gestures]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [xr-blocks, xrblocks-framework]
---

# XR Blocks

Lightweight JavaScript library from Google XR Labs for rapid AI + XR prototyping. Built on three.js, targets Chrome v136+ with WebXR on Android XR (Galaxy XR). Includes a desktop simulator for development.

## Key Capabilities

| Feature | Description |
|---------|-------------|
| Hand tracking | WebXR joints + TF Lite / PyTorch custom models |
| Gesture recognition | Pinch, open-palm, fist, thumbs-up, point, spread |
| Depth sensing | Depth mesh, depth texture, occlusion |
| Physics | Rapier engine (rigid bodies, colliders, gravity) |
| AI integration | Gemini (multimodal + live), OpenAI |
| 3D models | GLTF/GLB with Draco + KTX2 compression |
| UI system | Panels, grids, text, images, pagers |
| Desktop simulator | WASD + mouse, depth/hands simulation |

## Quick Start

```html
<script type="importmap">
{
  "imports": {
    "three": "https://cdn.jsdelivr.net/npm/three@0.182.0/build/three.module.js",
    "xrblocks": "https://cdn.jsdelivr.net/gh/google/xrblocks@build/xrblocks.js"
  }
}
</script>
```

```javascript
import * as xb from 'xrblocks';
class MyApp extends xb.Script {
  init() { /* set up scene */ }
  update() { /* per-frame logic */ }
  onSelectEnd(event) { /* handle pinch/click */ }
}
xb.add(new MyApp());
xb.init(new xb.Options());
```

## Resources

- GitHub: https://github.com/google/xrblocks
- Docs: https://xrblocks.github.io/docs/
- Gem (rapid prototyping): https://xrblocks.github.io/gem
- NPM: `xrblocks`
- Papers: arXiv:2509.25504, arXiv:2603.24591
- License: Apache 2.0 (not officially supported Google product)
