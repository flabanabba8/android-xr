# Android Studio Setup for XR

Fetched from: https://developer.android.com/develop/xr/jetpack-xr-sdk/get-studio
Date: 2026-05-21

## Requirements

- Latest Canary build of Android Studio
- Can install multiple versions side-by-side

## Steps

1. Close existing Android Studio versions
2. Download Canary from: https://developer.android.com/studio/preview
3. Extract, launch, follow wizard
4. Open SDK Manager (More Actions → SDK Manager)
5. SDK Tools tab — install:
   - Android SDK Build-Tools
   - Android Emulator
   - Android SDK Platform-Tools
   - Layout Inspector for API 31-36
6. Click Apply

## Virtual Device Setup

- XR headsets and glasses: /develop/xr/jetpack-xr-sdk/run/create-avds/xr-headsets-glasses
- Audio and display glasses: /develop/xr/jetpack-xr-sdk/run/create-avds/glasses

## Project Templates

New Project → XR → choose template:
- XR Headsets (immersive)
- Wired XR Glasses (immersive)
- Audio & Display Glasses (augmented)
