---
title: "Source: ASR, TTS, Gemini Live"
type: source-summary
tldr: "Audio I/O APIs for XR glasses"
sources:
  - raw/docs/asr-android-xr.md
  - raw/docs/tts-android-xr.md
  - raw/docs/gemini-live-xr.md
related:
  - "[[speech-recognition]]"
  - "[[live-captioning-architecture]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: []
---

# Source: ASR, TTS, Gemini Live for Android XR

**Sources:** developer.android.com ASR, TTS, and Gemini Live API docs for XR glasses
**Fetched:** 2026-05-21

## Key Takeaways

1. **SpeechRecognizer** is built-in, offline, no extra deps. Batch-mode (not streaming). RECORD_AUDIO permission required.
2. **ML Kit GenAI Speech Recognition** offers streaming via Kotlin Flow but is alpha. 15-21 languages. On-device Gemini model in Advanced mode (Pixel 10 only).
3. **TextToSpeech** is built-in, offline. QUEUE_FLUSH for immediate playback.
4. **Gemini Live API** via Firebase AI Logic handles bidirectional audio streaming but requires internet and costs money. Best for conversational AI, not pure transcription.
5. For live captioning: ML Kit GenAI (streaming) or SpeechRecognizer (batch fallback) are the right choices. Gemini Live is overkill.
