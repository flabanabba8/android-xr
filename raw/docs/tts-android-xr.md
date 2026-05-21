# Text to Speech (TTS) for Android XR

Source: https://developer.android.com/develop/xr/jetpack-xr-sdk/tts
Fetched: 2026-05-21

## Built-in, no additional libraries, works offline.

## Setup
```kotlin
tts = TextToSpeech(this) { status ->
    if (status == TextToSpeech.SUCCESS) { /* ready */ }
}
```

## Speak
```kotlin
tts?.speak("text", TextToSpeech.QUEUE_FLUSH, null, "utteranceId")
```

## Stop: tts?.stop()
## Cleanup: tts?.shutdown() in onDestroy()
