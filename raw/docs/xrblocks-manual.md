# XR Blocks Manual

Source: https://xrblocks.github.io/docs/
Fetched: 2026-05-21

## Scripts (Lifecycle)

Script class extends THREE.Object3D. Lifecycle methods:
- init() — called when found by Core. Async supported.
- update() — called every frame.

Global controller events: onSelectStart, onSelectEnd, onSelect, onSelecting, onSqueezeStart, onSqueezeEnd, onSqueeze, onSqueezing
Object-specific: onObjectSelectStart, onObjectSelectEnd, onHoverEnter, onHoverExit
Physics: initPhysics(physics), physicsStep()

Pattern:
```javascript
class MyClass extends xb.Script {
  async init() { await super.init(); }
  update() { }
}
```

ScriptMixin for multiple inheritance. Check isXRScript property.

## Core

Singleton at xb.core. Handles renderer, scene, input, depth, physics, simulator setup. Init:
```javascript
const options = new xb.Options();
xb.init(options);
```

## Inputs

Input at xb.core.input. Three controller types:
1. WebXR Input Sources (hand tracking, controllers)
2. MouseController (simulator User Mode)
3. GazeController (screen center for gaze interaction)

intersectionsForController maps controllers to raycast results. controller.userData.selected = true during selection.

## Hand Gestures

Optional. Enable with options.enableGestures().
Gestures: pinch, open-palm, fist, thumbs-up, point, spread
Events: gesturestart, gestureupdate, gestureend
Detail: { hand, name, confidence }
Default confidence threshold: 0.6, configurable via options.gestures.minimumConfidence
Provider: "heuristics" (WebXR joint-based)
Toggle individual: options.gestures.setGestureEnabled(name, bool)

## Depth

Enable: options.depth = new xb.DepthOptions(xrDepthMeshOptions)
Resume/pause: core.depth.resumeDepth(this) / core.depth.pauseDepth(this)
API: depthData, depthArray, rawValueToMeters, getDepth(u, v)
Depth Mesh: 40x40 default, 160x160 with useDownsampledGeometry: false
Depth Texture: options.depth.depthTexture.enabled = true
Occlusion: ModelViewer addOcclusionToShader: true, or OcclusionUtils.addOcclusionToShader()

## Physics

Rapier engine. Init with RAPIER import + physics options.
Controller at xb.core.physics. Default: 45 fps, gravity (0, -9.81, 0).
Scripts: initPhysics() for rigid bodies + colliders, physicsStep() for transform sync.
TypeScript: map rapier3d virtual import in tsconfig.json.
Examples: Ballpit, Drone samples.

## Simulator

Desktop development bridge. Import: 'xrblocks/addons/simulator/SimulatorAddons.js'
"Run in simulator" button appears on desktop.
Modes (toggle Left Shift):
- User Mode: WASD movement, mouse = MouseController, left click = select, right = rotate
- Navigation Mode: WASD movement, left click = rotate camera
Supports depth simulation and hands simulation. Does NOT emulate WebXR APIs.

## Integrations (Optional Dependencies)

- @dimforge/rapier3d — physics
- @google/genai — Gemini AI (multimodal, live API)
- openai — OpenAI text/chat
- lit — HTML UI overlays + simulator UI
- troika-three-text — SDF 3D text
- @sparkjsdev/spark — Gaussian splatting

Auto-detected when imported.

## Model Viewer

GLTF loading (Draco + KTX2 compression), THREE.Object3D support.
Interactive: move, rotate, scale via DragManager.
loadGLTFModel({ path, filename, scale }). Setup: setupBoundingBox(), setupRaycastCylinder/Box(), setupPlatform().

## Drag Manager

core.dragManager. Works with ModelViewer objects.
- Translation: select platform, move hand
- Rotation: select bounding cylinder/box, yaw only
- Scaling: select with both hands, distance-based
Custom objects: add draggable property, children with draggingMode (TRANSLATING/ROTATING).

## UI System

Built on View class (extends Script). Relative positioning -0.5 to 0.5. updateLayout() syncs.
Components:
- Panel: root view with rounded rectangle
- SpatialPanel: draggable panel
- GridView: subdivision layout
- ImageView: URL-based images
- TextView: troika-three-text rendering
- HorizontalPager/VerticalPager: scrollable pages with PagerState
- ScrollingTroikaTextView: auto-scrolling text
