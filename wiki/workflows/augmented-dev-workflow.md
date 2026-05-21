---
title: Augmented Development Workflow
type: workflow
tldr: "Build path for audio/display glasses"
sources:
  - raw/docs/jetpack-xr-overview.md
related:
  - "[[jetpack-xr-sdk]]"
  - "[[compose-glimmer]]"
  - "[[jetpack-projected]]"
  - "[[xr-device-types]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Augmented Development Workflow

Build path for audio and display glasses.

## Development Progression

```
1. Create glasses activity
    ↓
2. Check device capabilities and availability
    ↓
3. Build UI with Compose Glimmer
    ↓
4. Handle input (ASR, voice, touch)
    ↓
5. Integrate Text-to-Speech / Gemini Live API
    ↓
6. Request hardware permissions
    ↓
7. Access projected device hardware (cameras, sensors)
    ↓
8. Run on emulator / physical glasses
```

## Key Libraries

| Stage | Library |
|-------|---------|
| UI | [[compose-glimmer]] |
| Phone↔Glasses | [[jetpack-projected]] |
| Voice input | ASR (Automatic Speech Recognition) |
| AI | Gemini Live API |
| Notifications | Standard Android notification API (glasses behavior) |
