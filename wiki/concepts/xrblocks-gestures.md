---
title: XR Blocks Hand Gestures
type: concept
tldr: "Pinch, fist, point + custom gestures"
sources:
  - raw/docs/xrblocks-manual.md
related:
  - "[[xrblocks]]"
  - "[[xrblocks-architecture]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [xrblocks-hand-gestures]
---

# XR Blocks Hand Gestures

Optional gesture recognition system analyzing WebXR hand joints.

## Setup

```javascript
const options = new xb.Options();
options.enableGestures();
xb.init(options);
```

## Built-in Gestures

| Gesture | Detection |
|---------|-----------|
| Pinch | Thumb + index finger close |
| Open palm | All fingers extended |
| Fist | All fingers closed |
| Thumbs up | Thumb extended, others closed |
| Point | Index extended, others closed |
| Spread | All fingers extended and separated |

## Events

Subscribe via `xb.core.gestureRecognition`:

- **gesturestart** — gesture begins
- **gestureupdate** — gesture continues
- **gestureend** — gesture ends

Event detail: `{ hand, name, confidence }` (confidence: 0-1)

## Configuration

- **Threshold**: `options.gestures.minimumConfidence` (default 0.6)
- **Toggle**: `options.gestures.setGestureEnabled('pinch', true/false)`
- **Provider**: `"heuristics"` (WebXR joint-based, currently only option)
- **Custom models**: TensorFlow Lite or PyTorch for custom gesture recognition
