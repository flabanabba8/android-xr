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

## Strategy: Quest First → Catalyst → Wired Glasses

**Phase 1 (now):** Build MVP on **Meta Quest 3S** — hardware we have. OpenXR passthrough + spatial caption panel.

**Phase 2 (grant):** Submit Quest MVP to [[catalyst-program]] as proof of developer readiness. Apply for wired glasses dev kit + funding.

**Phase 3 (post-grant):** Port/rebuild for **wired XR glasses** (Project Aura) using Jetpack XR SDK / Compose for XR. The caption pipeline (ASR → translate → display) is device-agnostic — only the display layer changes.

## Pipeline

```
Microphone → ASR (speech→text) → Language ID → Translation → Caption Renderer
   ↓              ↓                    ↓              ↓              ↓
 RECORD_AUDIO   SpeechRecognizer  ML Kit LangID  ML Kit Trans   Platform-specific
 permission     onPartialResults  (auto-detect)  (50+ langs)    display layer
```

## Target Devices

| Phase | Device | SDK | Caption Display |
|-------|--------|-----|----------------|
| **1 (MVP)** | Meta Quest 3S | OpenXR + passthrough | Floating panel in passthrough mode |
| **2 (Grant)** | Wired XR Glasses (Aura) | Compose for XR | SpatialPanel in see-through space |
| *Stretch* | Display Glasses | Compose Glimmer | Glanceable text card |

## Component Selection

### Speech Recognition
- **Primary**: `SpeechRecognizer` with `onPartialResults()` for streaming transcription. Built-in, offline, no deps. Use `LANGUAGE_MODEL_FREE_FORM` for continuous speech.
- **Upgrade path**: ML Kit GenAI Speech Recognition (streaming Kotlin Flow, 21 languages) when it leaves alpha.
- **Why not Gemini Live**: Requires internet, costs money, overkill for pure transcription.

### Language Detection
- **ML Kit Language ID** (`com.google.mlkit:language-id`) — 100+ languages, on-device, ~900KB
- Auto-detect source language from ASR output text
- Confidence threshold: 0.5 default (configurable)

### Translation
- **ML Kit Translation** (`com.google.mlkit:translate:17.0.3`) — on-device, 50+ languages, ~30MB/model
- Pre-download top 5 language pairs at first launch (avoid latency during live use)
- Use Language ID result to set source language dynamically

### Caption Display

**Quest 3S (Phase 1):**
- OpenXR passthrough mode — see real world with caption overlay
- Spatial text panel positioned at comfortable reading distance (~1.5m)
- Head-locked by default (follows gaze like subtitles)
- High-contrast white text on semi-transparent dark background

**Wired Glasses (Phase 2):**
- [[spatial-ui]] SpatialPanel via Compose for XR
- Additive display: bright text on transparent background (dark = invisible on glasses)
- World-anchored option for seated conversations

## Key Design Decisions

1. **Head-locked captions** (default) — follows gaze like subtitles. Toggle to world-anchored for seated settings. Head-locked is more readable but can cause fatigue in long sessions.
2. **Rolling window** — last 3-4 lines visible, older lines fade. Configurable persistence.
3. **Font size: 20dp minimum** — bigger than XR guideline minimum (14dp). Hearing-impaired users need larger text. Must be user-configurable.
4. **Privacy-first** — all processing on-device. No audio or text leaves the device. Critical for medical/personal conversations.
5. **Latency budget**: ASR (~200ms) + LangID (~50ms) + translation (~100ms) + render (~50ms). Target **<500ms** total.

## Module Architecture

```
core/               # Device-agnostic pipeline (shared)
├── CaptionPipeline.kt        # ASR → LangID → Translate orchestration
├── SpeechRecognizerManager.kt # Wraps SpeechRecognizer, emits Flow<PartialResult>
├── LanguageDetector.kt        # ML Kit LangID wrapper
└── Translator.kt             # ML Kit Translate + model management

quest/              # Phase 1: Quest 3S specific
├── QuestCaptionPanel.kt      # OpenXR passthrough + spatial text
└── QuestActivity.kt          # Quest entry point

glasses/            # Phase 2: Wired glasses specific
├── GlassesCaptionPanel.kt    # Compose for XR SpatialPanel
└── GlassesActivity.kt        # Projected activity

shared/             # Preferences, settings
├── LanguageSelector.kt
├── DisplaySettings.kt        # Font size, position, contrast, mode
└── ModelDownloadManager.kt   # Pre-download language models
```

## Catalyst Grant Fit

- **Vertical**: Health & Wellness / Accessibility
- **Pitch**: Quest MVP proves the pipeline works. Grant funds the glasses port + accessibility user testing.
- **67M+ Americans** with hearing difficulty — genuine, large market need
- **On-device = privacy** — no cloud dependency for personal/medical conversations
- **Multi-platform = wider impact** — Quest today, Google glasses tomorrow
- **Developer readiness**: Working Quest prototype by application deadline (June 30)
