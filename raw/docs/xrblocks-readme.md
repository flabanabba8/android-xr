# XR Blocks

Source: https://github.com/google/xrblocks
Fetched: 2026-05-21

## Description

Lightweight JavaScript library for rapid AI + XR prototyping. Built on three.js, targets Chrome v136+ with WebXR on Android XR (Galaxy XR). Includes desktop simulator.

## Features

- Hand Tracking & Gestures: TensorFlow Lite / PyTorch models, custom gestures
- Gesture Recognition: pinch, open-palm, fist, thumbs-up, point, spread
- World Understanding: depth sensing, geometry-aware physics, Gemini object recognition
- AI Integration: Gemini multimodal understanding + live conversational
- Cross-Platform: XR devices + desktop Chrome

## Setup

```bash
git clone --depth=1 git@github.com:google/xrblocks.git
cd xrblocks
npm ci
npm run serve  # http://localhost:8080
npm run dev    # watch mode + serve
```

## Basic Usage

```html
<script type="importmap">
{
  "imports": {
    "three": "https://cdn.jsdelivr.net/npm/three@0.182.0/build/three.module.js",
    "three/addons/": "https://cdn.jsdelivr.net/npm/three@0.182.0/examples/jsm/",
    "xrblocks": "https://cdn.jsdelivr.net/gh/google/xrblocks@build/xrblocks.js",
    "xrblocks/addons/": "https://cdn.jsdelivr.net/gh/google/xrblocks@build/addons/"
  }
}
</script>
```

```javascript
import * as THREE from 'three';
import * as xb from 'xrblocks';

class MainScript extends xb.Script {
  init() {
    this.add(new THREE.HemisphereLight(0xffffff, 0x666666, 3));
    const geometry = new THREE.CylinderGeometry(0.2, 0.2, 0.4, 32);
    const material = new THREE.MeshPhongMaterial({ color: 0xffffff, transparent: true, opacity: 0.8 });
    this.player = new THREE.Mesh(geometry, material);
    this.player.position.set(0, xb.user.height - 0.5, -xb.user.objectDistance);
    this.add(this.player);
  }
  onSelectEnd(event) {
    this.player.material.color.set(Math.random() * 0xffffff);
  }
}

document.addEventListener('DOMContentLoaded', function () {
  xb.add(new MainScript());
  xb.init(new xb.Options());
});
```

## Project Structure

src/: addons, agent, ai, camera, core, depth, input, lighting, physics, simulator, sound, stereo, ui, utils, ux, video, world

## Resources

- Docs: https://xrblocks.github.io/docs/
- Templates: https://xrblocks.github.io/docs/templates/Basic/
- Gem (rapid prototyping): https://xrblocks.github.io/gem
- YouTube: https://www.youtube.com/watch?v=75QJHTsAoB8
- Papers: arXiv:2509.25504, arXiv:2603.24591
- NPM: xrblocks
- License: Apache 2.0
- Not an officially supported Google product
