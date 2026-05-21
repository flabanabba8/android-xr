---
title: XR Blocks Depth & Physics
type: concept
tldr: "Depth sensing + Rapier physics engine"
sources:
  - raw/docs/xrblocks-manual.md
related:
  - "[[xrblocks]]"
  - "[[xrblocks-architecture]]"
  - "[[arcore-xr]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# XR Blocks Depth & Physics

## Depth Sensing

Enable: `options.depth = new xb.DepthOptions(xrDepthMeshOptions)`

Per-object control: `core.depth.resumeDepth(this)` / `pauseDepth(this)`. Auto-pauses when nothing needs it.

### Depth Mesh
3D projection of depth values attached to left camera.
- Default: 40x40 downsampled
- Full resolution: `useDownsampledGeometry: false` (160x160)
- Collider update rate: `depthMesh.colliderUpdateFps`

### Depth Texture
GPU-stored for shaders: `options.depth.depthTexture.enabled = true`

### Occlusion
Virtual objects hidden behind real-world surfaces.
- ModelViewer: `addOcclusionToShader: true`
- Custom: `OcclusionUtils.addOcclusionToShader()`

### API
- `depthData` — left/right depth objects (data, width, height, rawValueToMeters)
- `getDepth(u, v)` — left camera depth from normalized coordinates

## Physics (Rapier)

Import RAPIER + pass through physics options. Controller at `xb.core.physics`.

### Defaults
- Fixed update: 45 fps
- Gravity: (0, -9.81, 0) m/s²
- Auto world stepping

### Implementation
```javascript
initPhysics(physics) {
  // Create RAPIER rigid body + colliders
}
physicsStep() {
  // Copy body translation/rotation to 3D object
}
```

### Examples
Ballpit sample, Drone sample in repo.
