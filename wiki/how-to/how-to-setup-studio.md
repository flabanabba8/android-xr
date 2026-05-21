---
title: "How to: Set Up Android Studio for XR"
type: how-to
tldr: "Install Canary build + XR tools"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[android-studio-xr]]"
  - "[[getting-started-xr]]"
  - "[[how-to-create-project]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# How to Set Up Android Studio for XR

## Steps

1. Download the **latest Canary build** of Android Studio (stable may not include XR tools)
2. Install and launch
3. Open SDK Manager → ensure Android SDK 34+ is installed
4. Open AVD Manager → create an XR virtual device (headset or glasses)
5. Verify XR project templates appear in File → New → New Project

## Why Canary?

XR tooling (emulator, Layout Inspector for spatial UI, composable previews) is only available in Canary builds during the Developer Preview phase.
