# ML Kit On-Device Translation

Source: https://developers.google.com/ml-kit/language/translation/android
Fetched: 2026-05-21

## Overview
On-device translation, 50+ languages, ~30MB per model, API 23+.

## Setup
```gradle
implementation 'com.google.mlkit:translate:17.0.3'
```

## Create Translator
```kotlin
val options = TranslatorOptions.Builder()
    .setSourceLanguage(TranslateLanguage.ENGLISH)
    .setTargetLanguage(TranslateLanguage.GERMAN)
    .build()
val translator = Translation.getClient(options)
```

## Download Model
```kotlin
val conditions = DownloadConditions.Builder().requireWifi().build()
translator.downloadModelIfNeeded(conditions)
    .addOnSuccessListener { /* ready */ }
    .addOnFailureListener { /* error */ }
```

## Translate
```kotlin
translator.translate(text)
    .addOnSuccessListener { translatedText -> }
    .addOnFailureListener { }
```

## Model Management
```kotlin
val modelManager = RemoteModelManager.getInstance()
modelManager.getDownloadedModels(TranslateRemoteModel::class.java)
modelManager.deleteDownloadedModel(germanModel)
modelManager.download(frenchModel, conditions)
```

## Language Detection
Use Language Identification API: TranslateLanguage.fromLanguageTag(tag)

## Cleanup: translator.close() or use LifecycleObserver
