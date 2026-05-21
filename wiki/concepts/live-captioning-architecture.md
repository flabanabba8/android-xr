---
title: Live Captioning Architecture
type: concept
tldr: "ASR → translate → spatial display pipeline"
sources:
  - raw/docs/asr-android-xr.md
  - raw/docs/ml-kit-translation.md
  - raw/docs/jetpack-xr-overview.md
  - raw/docs/ai-glasses-design.md
related:
  - "[[speech-recognition]]"
  - "[[ml-kit-translation]]"
  - "[[spatial-ui]]"
  - "[[compose-glimmer]]"
  - "[[compose-for-xr]]"
  - "[[meta-quest-openxr]]"
  - "[[catalyst-program]]"
created: 2026-05-21
updated: 2026-05-21
confidence: medium
last_verified: 2026-05-21
aliases: [captioning-pipeline, transcription-architecture]
---

# Live Captioning Architecture

Real-time speech transcription + translation for hearing-impaired users on XR devices.

## Pipeline

```
Microphone → ASR (speech→text) → Language ID → Translation → Spatial Display
   ↓              ↓                    ↓              ↓             ↓
 RECORD_AUDIO   ML Kit GenAI     ML Kit LangID   ML Kit Trans   SpatialPanel
 permission     or SpeechRec     (auto-detect)   (50+ langs)    or Glimmer
```

## Target Devices

| Device | Display | SDK | Caption Approach |
|--------|---------|-----|-----------------|
| **Meta Quest** | Immersive HMD | OpenXR / Compose for XR | SpatialPanel floating in view |
| **Wired XR Glasses** (Project Aura) | Spatial see-through | Compose for XR | SpatialPanel anchored in space |
| **Display Glasses** | Overlay see-through | Compose Glimmer | Glanceable text card |

## Component Selection

### Speech Recognition
- **Primary**: ML Kit GenAI Speech Recognition (streaming, on-device, 21 languages)
- **Fallback**: SpeechRecognizer (built-in, offline, simpler but batch-only)
- **Consideration**: GenAI is alpha — may need SpeechRecognizer as production fallback

### Translation
- **ML Kit Translation** (on-device, 50+ languages, ~30MB per model)
- Pre-download common language pairs at app setup
- Use Language Identification to auto-detect source language

### Display
- **Headset/Wired Glasses**: [[spatial-ui]] SpatialPanel with large, high-contrast text. Position at comfortable reading distance. Consider anchoring to user view (head-locked) vs world-anchored.
- **Display Glasses**: [[compose-glimmer]] text card. Minimal, glanceable. High contrast for optical see-through.
- **Audio Glasses**: TTS output of translated text (no visual display available)

## Key Design Decisions

1. **Head-locked vs world-anchored captions**: Head-locked is more readable but can cause fatigue. World-anchored requires spatial tracking. Consider user preference toggle.
2. **Caption persistence**: How long to show each line? Rolling window vs fade-out.
3. **Speaker identification**: Can we identify different speakers? (Directional audio + ML)
4. **Font size and contrast**: Must be configurable — hearing-impaired users have diverse needs.
5. **Latency budget**: ASR (~200ms) + translation (~100ms) + render. Target <500ms total.

## Accessibility Vertical Fit

This app fits the **Health & Wellness** and **Accessibility** verticals for the [[catalyst-program]]. Key selling points:
- Genuine accessibility need (67M+ Americans with hearing difficulty)
- On-device processing = privacy (critical for medical/personal conversations)
- Multi-device support = wider impact
- Translation adds value for multilingual settings
