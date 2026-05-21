---
title: "Source: Jetpack XR SDK Documentation"
type: source-summary
tldr: "Official SDK docs overview"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[jetpack-xr-sdk]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Source: Jetpack XR SDK Documentation

**Source:** developer.android.com/develop/xr/jetpack-xr-sdk  
**Fetched:** 2026-05-21  

## Key Takeaways

1. **Three device categories** with distinct SDK surfaces: headsets/wired glasses use Compose for XR + SceneCore; display glasses use Glimmer + Projected
2. **Six core libraries**: Compose for XR, Material Design for XR, SceneCore, ARCore for XR, Compose Glimmer, Jetpack Projected
3. **Two development paths**: immersive (spatial UI → 3D → perception) and augmented (glasses activity → Glimmer → voice/AI → hardware)
4. **Developer Preview 4** status — actively evolving, known issues tracked
5. **ProGuard gotcha**: extensions-xr dependency must be `compileOnly` (not implementation)
