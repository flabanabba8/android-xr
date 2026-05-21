# Meta Quest OpenXR Development

Source: https://developers.meta.com/horizon/documentation/native/android/mobile-openxr/
Fetched: 2026-05-21

## Platform: Quest 1, 2, 3, Pro. OpenXR 1.0 adopters. OS v62+.

## SDK
Download Oculus OpenXR Mobile SDK.
GitHub: https://github.com/meta-quest/Meta-OpenXR-SDK
Version 85.0 (Feb 2026)

## Gradle
```gradle
buildFeatures { prefab true }
dependencies {
    implementation 'org.khronos.openxr:openxr_loader_for_android:1.0.34'
}
```

## Manifest
```xml
<intent-filter>
    <category android:name="org.khronos.openxr.intent.category.IMMERSIVE_HMD" />
</intent-filter>
```

## Required Extensions
- XR_KHR_loader_init + XR_KHR_loader_init_android
- XR_KHR_android_create_instance

## Features
- Hand tracking (skinned mesh, collision capsules, pinch UI)
- Passthrough (basic, masked, projected + style options)
- Spatial anchors (handle, maintain, share)
- Eye tracking (gaze data)
- Face tracking (blendshape weights)
- Body tracking (skeleton joints)
- Scene understanding (floor, wall, furniture)
- Dynamic object tracking (keyboard on Quest 3+)
- Application SpaceWarp (performance)
- Virtual keyboard
- Microgestures (thumb swipe, tap)
- Color space support (XR_FB_color_space)
