# Automatic Speech Recognition (ASR) for Android XR

Source: https://developer.android.com/develop/xr/jetpack-xr-sdk/asr
Fetched: 2026-05-21

## Overview
Built-in SpeechRecognizer, no additional libraries, works offline. For audio & display glasses.

## Permission: RECORD_AUDIO required before instantiation.

## Setup
```kotlin
speechRecognizer = SpeechRecognizer.createOnDeviceSpeechRecognizer(this)
speechRecognizer?.setRecognitionListener(recognitionListener)
```

## Recognition Listener
```kotlin
val recognitionListener = object : RecognitionListener {
    override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(RESULTS_RECOGNITION)
        val confidences = results?.getFloatArray(CONFIDENCE_SCORES)
        val mostConfidentIndex = confidences!!.indices.maxByOrNull { confidences[it] }
        if (mostConfidentIndex != null) {
            val spokenText = matches[mostConfidentIndex]
        }
    }
}
```

## Start Listening
```kotlin
val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
}
speechRecognizer?.startListening(intent)
```

## Matching: highest confidence (fewer false positives) or any match (catches more).

## ML Kit GenAI Speech Recognition (alternative)
- dependency: com.google.mlkit:genai-speech-recognition:1.0.0-alpha1
- Basic mode: API 31+, 15 languages
- Advanced mode: Pixel 10 only, 21 languages, on-device Gemini model
- Streaming via Kotlin Flow
- Audio: 16-bit PCM mono 16kHz
- Status: alpha
