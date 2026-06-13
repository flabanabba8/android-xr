# XR-Transcript

Real-time live captioning app for Meta Quest 3S. Runs Whisper speech recognition entirely on-device — no internet, no cloud, no data leaves the headset.

## Features

- **Whisper small.en** (244M params, int8) via sherpa-onnx / ONNX Runtime
- **Silero VAD** — only transcribes when speech is detected, no hallucinations from silence
- **Echo cancellation** — strips Quest system audio from mic input
- **Noise suppression + auto gain control**
- High-contrast dark UI optimized for passthrough mode

## Build

```bash
# 1. Download models (one-time, ~360MB)
./download-models.sh

# 2. Build APK
./gradlew :caption:assembleDebug

# 3. Sideload to Quest (developer mode + USB debugging required)
adb install -r caption/build/outputs/apk/debug/caption-debug.apk
```

Find the app in Quest Library → Unknown Sources.

## Requirements

- JDK 17+
- Android SDK (API 34, build-tools 34)
- Meta Quest 3S in developer mode

## Architecture

```
caption/        Main app (Kotlin + Jetpack Compose)
├── asr/        SherpaEngine — audio capture, VAD, Whisper transcription
├── caption/    CaptionBuffer + ViewModel — rolling caption display
├── ui/         Dark theme, caption display, controls
└── libs/       sherpa-onnx.aar (pre-built ONNX Runtime + JNI)

models/         Whisper + VAD model assets (downloaded via script)
```
