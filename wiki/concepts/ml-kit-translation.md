---
title: ML Kit On-Device Translation
type: concept
tldr: "50+ languages, ~30MB models, offline"
sources:
  - raw/docs/ml-kit-translation.md
related:
  - "[[speech-recognition]]"
  - "[[live-captioning-architecture]]"
created: 2026-05-21
updated: 2026-05-21
confidence: high
last_verified: 2026-05-21
aliases: [ml-kit-translate, on-device-translation]
---

# ML Kit On-Device Translation

On-device translation between 50+ languages. Models ~30MB each, downloaded on demand. API 23+.

## Setup

```gradle
implementation 'com.google.mlkit:translate:17.0.3'
```

## Usage

```kotlin
// Create translator
val options = TranslatorOptions.Builder()
    .setSourceLanguage(TranslateLanguage.ENGLISH)
    .setTargetLanguage(TranslateLanguage.SPANISH)
    .build()
val translator = Translation.getClient(options)

// Download model (do this ahead of time)
translator.downloadModelIfNeeded(DownloadConditions.Builder().requireWifi().build())
    .addOnSuccessListener { /* ready */ }

// Translate
translator.translate("Hello, how are you?")
    .addOnSuccessListener { translated -> /* "Hola, ¿cómo estás?" */ }
```

## Model Management

```kotlin
val modelManager = RemoteModelManager.getInstance()
// List downloaded
modelManager.getDownloadedModels(TranslateRemoteModel::class.java)
// Pre-download
modelManager.download(TranslateRemoteModel.Builder(TranslateLanguage.FRENCH).build(), conditions)
// Delete unused
modelManager.deleteDownloadedModel(model)
```

## Language Detection

Use ML Kit Language Identification API for unknown input:
```kotlin
TranslateLanguage.fromLanguageTag(detectedTag)
```

## For Live Captioning

Pipeline: ASR (speech→text) → Language ID (detect source) → ML Kit Translate (source→target) → display on [[spatial-ui]] panel or [[compose-glimmer]] surface.

Pre-download common language pairs during setup to avoid latency during live use.
