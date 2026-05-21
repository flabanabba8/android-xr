---
title: Getting Started with Android XR
type: workflow
tldr: "First XR project in 5 steps"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[how-to-setup-studio]]"
  - "[[how-to-create-project]]"
  - "[[android-studio-xr]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Getting Started with Android XR

## Steps

1. **Install Android Studio** — latest Canary build. See [[how-to-setup-studio]].
2. **Create XR project** — File → New → New Project → select XR template for target device type. See [[how-to-create-project]].
3. **Add SDK dependencies** — Jetpack XR SDK libraries in `build.gradle.kts`. minSdk 24, compileSdk 34+.
4. **Run on emulator** — create an XR AVD (headset or glasses), launch.
5. **Iterate** — use Layout Inspector for spatial UI debugging, Composable Previews for fast iteration.

## ProGuard Gotcha

If using code minification, add:
```kotlin
dependencies {
    compileOnly("com.android.extensions.xr:extensions-xr:1.1.0")
}
```
Must be `compileOnly` — `implementation` or `api` breaks runtime.
