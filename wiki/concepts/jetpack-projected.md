---
title: Jetpack Projected
type: concept
tldr: "Phone-to-glasses communication"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[compose-glimmer]]"
  - "[[xr-device-types]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [projected, jetpack-projected-api]
---

# Jetpack Projected

Phone-to-glasses communication layer for audio and display glasses. The phone acts as the host device; the glasses are the projected device.

## Capabilities

- Access projected device hardware (cameras, sensors) from phone app
- Projected contexts for running code on the glasses without a full activity
- Permission request helpers for glasses hardware
- Device capability and display state checking
- App camera action integration

## Architecture

```
Phone (host)  ←→  Glasses (projected)
  App logic         Display + sensors
  Heavy compute     Lightweight rendering
  User input        Camera, microphone
```

## When to Use

Use Projected when building for audio/display glasses that tether to a phone. The phone does the heavy lifting; the glasses provide the display surface and sensor access.
