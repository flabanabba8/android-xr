---
title: "How to: Create an Android XR Project"
type: how-to
tldr: "New project from XR template"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[getting-started-xr]]"
  - "[[how-to-setup-studio]]"
  - "[[jetpack-xr-sdk]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# How to Create an Android XR Project

## Steps

1. Open Android Studio (Canary build)
2. File → New → New Project
3. Select the **XR template** matching your target device type
4. Click Next, choose project name, click Finish
5. Add Jetpack XR SDK dependencies to `build.gradle.kts`:
   - minSdk: 24
   - compileSdk: 34+

## ProGuard Configuration

If using code minification:

```kotlin
dependencies {
    // Required for ProGuard with Jetpack XR alpha05+
    compileOnly("com.android.extensions.xr:extensions-xr:1.1.0")
}
```

**Must be `compileOnly`** — using `implementation` or `api` causes runtime crashes.
