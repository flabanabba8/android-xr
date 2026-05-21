# ML Kit Language Identification

Source: https://developers.google.com/ml-kit/language/identification/android
Fetched: 2026-05-21

## 100+ languages, native + romanized scripts. API 23+.

## Setup (bundled, ~900KB)
```gradle
implementation 'com.google.mlkit:language-id'
```

## Identify Single Language
```kotlin
val identifier = LanguageIdentification.getClient()
identifier.identifyLanguage(text)
    .addOnSuccessListener { code -> /* BCP-47 code, "und" if unsure */ }
```

## Identify Multiple Possible Languages
```kotlin
identifier.identifyPossibleLanguages(text)
    .addOnSuccessListener { langs ->
        for (lang in langs) { lang.languageTag; lang.confidence }
    }
```

## Custom Threshold
```kotlin
LanguageIdentification.getClient(
    LanguageIdentificationOptions.Builder()
        .setConfidenceThreshold(0.34f)
        .build()
)
```

Defaults: identifyLanguage() = 0.5, identifyPossibleLanguages() = 0.01
Returns "und" when below threshold.
