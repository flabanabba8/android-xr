# Jetpack XR SDK Setup Reference

Fetched from: https://developer.android.com/develop/xr/jetpack-xr-sdk/set-up-sdk
Date: 2026-05-21

## Requirements

- minSdk: 24+
- compileSdk: 34+

## Immersive Experiences (Headsets & Wired Glasses)

```kotlin
dependencies {
    implementation("androidx.xr.runtime:runtime:1.0.0-alpha14")
    implementation("androidx.xr.scenecore:scenecore:1.0.0-alpha15")
    implementation("androidx.xr.compose:compose:1.0.0-alpha14")
    implementation("androidx.xr.compose.material3:material3:1.0.0-alpha17")
    implementation("androidx.xr.arcore:arcore:1.0.0-alpha14")
}
```

## Augmented Experiences (AI Glasses)

```kotlin
dependencies {
    implementation("androidx.xr.runtime:runtime:1.0.0-alpha14")
    implementation("androidx.xr.glimmer:glimmer:1.0.0-alpha12")
    implementation("androidx.xr.glimmer:glimmer-google-fonts:1.0.0-alpha12")
    implementation("androidx.xr.projected:projected:1.0.0-alpha07")
    implementation("androidx.xr.arcore:arcore:1.0.0-alpha13")
}
```

Note: Use specified versions for AI glasses even if newer exist.

## ProGuard (alpha05+)

```kotlin
dependencies {
    compileOnly("com.android.extensions.xr:extensions-xr:1.3.0")
}
```

MUST be compileOnly. implementation/api breaks runtime.

## Release Notes Links

- XR Runtime: /jetpack/androidx/releases/xr-runtime
- SceneCore: /jetpack/androidx/releases/xr-scenecore
- Compose for XR: /jetpack/androidx/releases/xr-compose
- Material3 for XR: /jetpack/androidx/releases/xr-compose-material3
- ARCore for XR: /jetpack/androidx/releases/xr-arcore
- Glimmer: /jetpack/androidx/releases/xr-glimmer
- Projected: /jetpack/androidx/releases/xr-projected
