package com.xrcaption.quest.asr

import android.content.Context
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import org.vosk.Model
import org.vosk.Recognizer
import org.vosk.android.RecognitionListener
import org.vosk.android.SpeechService
import org.vosk.android.StorageService
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class VoskEngine {

    private var model: Model? = null
    private var speechService: SpeechService? = null

    private val _state = MutableStateFlow<AsrState>(AsrState.Idle)
    val state: StateFlow<AsrState> = _state

    private val _partialResults = MutableSharedFlow<String>(extraBufferCapacity = 16)
    val partialResults: SharedFlow<String> = _partialResults

    private val _finalResults = MutableSharedFlow<String>(extraBufferCapacity = 16)
    val finalResults: SharedFlow<String> = _finalResults

    suspend fun loadModel(context: Context) {
        if (model != null) {
            _state.value = AsrState.Ready
            return
        }
        _state.value = AsrState.Loading
        try {
            model = unpackModel(context)
            _state.value = AsrState.Ready
        } catch (e: Exception) {
            _state.value = AsrState.Error(e.message ?: "Failed to load model")
        }
    }

    private suspend fun unpackModel(context: Context): Model =
        suspendCancellableCoroutine { cont ->
            StorageService.unpack(
                context,
                "model-en-us",
                "model",
                { loadedModel ->
                    cont.resume(loadedModel)
                },
                { exception ->
                    cont.resumeWithException(
                        IOException("Model unpack failed: ${exception.message}")
                    )
                }
            )
        }

    fun startListening() {
        val currentModel = model ?: return
        if (_state.value == AsrState.Listening) return

        val recognizer = Recognizer(currentModel, 16000.0f)
        val service = SpeechService(recognizer, 16000.0f)

        service.startListening(object : RecognitionListener {
            override fun onPartialResult(hypothesis: String?) {
                hypothesis ?: return
                val text = extractText(hypothesis, "partial")
                if (text.isNotBlank()) {
                    _partialResults.tryEmit(text)
                }
            }

            override fun onResult(hypothesis: String?) {
                hypothesis ?: return
                val text = extractText(hypothesis, "text")
                if (text.isNotBlank()) {
                    _finalResults.tryEmit(text)
                }
            }

            override fun onFinalResult(hypothesis: String?) {
                hypothesis ?: return
                val text = extractText(hypothesis, "text")
                if (text.isNotBlank()) {
                    _finalResults.tryEmit(text)
                }
            }

            override fun onError(exception: Exception?) {
                _state.value = AsrState.Error(exception?.message ?: "Recognition error")
            }

            override fun onTimeout() {
                stopListening()
            }
        })

        speechService = service
        _state.value = AsrState.Listening
    }

    fun stopListening() {
        speechService?.stop()
        speechService = null
        if (_state.value == AsrState.Listening) {
            _state.value = AsrState.Ready
        }
    }

    fun shutdown() {
        stopListening()
        model?.close()
        model = null
        _state.value = AsrState.Idle
    }

    private fun extractText(json: String, key: String): String {
        return try {
            JSONObject(json).optString(key, "")
        } catch (_: Exception) {
            ""
        }
    }
}
