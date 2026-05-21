---
title: Speech Recognition for XR
type: concept
tldr: "ASR options: built-in, ML Kit, Gemini"
sources:
  - raw/docs/asr-android-xr.md
  - raw/docs/gemini-live-xr.md
related:
  - "[[ml-kit-translation]]"
  - "[[live-captioning-architecture]]"
  - "[[jetpack-xr-sdk]]"
  - "[[compose-glimmer]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [asr, speech-to-text, stt]
---

# Speech Recognition for XR

Three options for converting speech to text on Android XR, each with different tradeoffs.

## Option Comparison

| Feature | SpeechRecognizer | ML Kit GenAI | Gemini Live |
|---------|-----------------|--------------|-------------|
| Offline | Yes | Yes | No |
| Streaming | No (batch) | Yes (Kotlin Flow) | Yes (bidirectional) |
| Languages | System default | 15 basic / 21 advanced | Many |
| Latency | Low | Low | Medium (network) |
| Cost | Free | Free | Paid |
| Min API | 24 | 31 (basic) / Pixel 10 (advanced) | Any (needs internet) |
| Best for | Command recognition | **Continuous transcription** | Conversational AI |

## For Live Captioning: ML Kit GenAI is the best fit

- Streaming partial→final results via Kotlin Flow
- On-device (privacy, no latency)
- 16-bit PCM mono 16kHz input
- Broader language coverage in Advanced mode

## SpeechRecognizer (built-in)

```kotlin
speechRecognizer = SpeechRecognizer.createOnDeviceSpeechRecognizer(this)
speechRecognizer?.setRecognitionListener(recognitionListener)
val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
}
speechRecognizer?.startListening(intent)
```

Permission: `RECORD_AUDIO` required before instantiation.

## ML Kit GenAI Speech Recognition

```gradle
implementation("com.google.mlkit:genai-speech-recognition:1.0.0-alpha1")
```

Streaming flow: create recognizer → check/download model → startRecognition() → collect Flow → stopRecognition().

**Status: Alpha** — no SLA or deprecation guarantee.
