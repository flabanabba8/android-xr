package com.xrcaption.quest.asr

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import androidx.core.content.ContextCompat
import com.k2fsa.sherpa.onnx.OfflineModelConfig
import com.k2fsa.sherpa.onnx.OfflineRecognizer
import com.k2fsa.sherpa.onnx.OfflineRecognizerConfig
import com.k2fsa.sherpa.onnx.OfflineWhisperModelConfig
import com.k2fsa.sherpa.onnx.SileroVadModelConfig
import com.k2fsa.sherpa.onnx.Vad
import com.k2fsa.sherpa.onnx.VadModelConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SherpaEngine {

    companion object {
        private const val TAG = "SherpaEngine"
        private const val SAMPLE_RATE = 16000
        private const val MODEL_DIR = "sherpa-onnx-whisper-small.en"
        private const val VAD_MODEL = "silero_vad.onnx"
        private const val VAD_WINDOW = 512
    }

    private var recognizer: OfflineRecognizer? = null
    private var vad: Vad? = null
    private var audioRecord: AudioRecord? = null
    private var captureJob: Job? = null
    private var transcribeJob: Job? = null
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var noiseSuppressor: android.media.audiofx.NoiseSuppressor? = null
    private var gainControl: android.media.audiofx.AutomaticGainControl? = null

    // Channel to pass speech segments from capture loop to transcription loop
    // Created per listening session to avoid stale data across start/stop cycles
    private var segmentChannel = Channel<FloatArray>(capacity = 4)

    private val _state = MutableStateFlow<AsrState>(AsrState.Idle)
    val state: StateFlow<AsrState> = _state

    private val _statusText = MutableStateFlow("")
    val statusText: StateFlow<String> = _statusText

    private val _partialResults = MutableSharedFlow<String>(extraBufferCapacity = 16)
    val partialResults: SharedFlow<String> = _partialResults

    private val _finalResults = MutableSharedFlow<String>(extraBufferCapacity = 16)
    val finalResults: SharedFlow<String> = _finalResults

    suspend fun loadModel(context: Context) {
        if (recognizer != null) {
            _state.value = AsrState.Ready
            return
        }
        _state.value = AsrState.Loading
        _statusText.value = "Loading models..."
        try {
            val loadStart = System.currentTimeMillis()

            recognizer = withContext(Dispatchers.IO) {
                createRecognizer(context)
            }
            Log.i(TAG, "Whisper model loaded")

            vad = withContext(Dispatchers.IO) {
                createVad(context)
            }
            Log.i(TAG, "VAD model loaded")

            val loadTime = System.currentTimeMillis() - loadStart
            _state.value = AsrState.Ready
            _statusText.value = "Ready (${loadTime}ms)"
            Log.i(TAG, "All models loaded in ${loadTime}ms")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to load models", e)
            _state.value = AsrState.Error(e.message ?: "Failed to load models")
            _statusText.value = "Load failed: ${e.message}"
        }
    }

    private fun createRecognizer(context: Context): OfflineRecognizer {
        val whisperConfig = OfflineWhisperModelConfig(
            encoder = "$MODEL_DIR/small.en-encoder.int8.onnx",
            decoder = "$MODEL_DIR/small.en-decoder.int8.onnx",
            language = "en",
            task = "transcribe"
        )
        val modelConfig = OfflineModelConfig(
            whisper = whisperConfig,
            tokens = "$MODEL_DIR/small.en-tokens.txt",
            numThreads = 4,
            debug = false
        )
        val config = OfflineRecognizerConfig(
            modelConfig = modelConfig,
            decodingMethod = "greedy_search"
        )
        return OfflineRecognizer(context.assets, config)
    }

    private fun createVad(context: Context): Vad {
        val sileroConfig = SileroVadModelConfig(
            model = VAD_MODEL,
            threshold = 0.5f,
            minSilenceDuration = 0.3f,
            minSpeechDuration = 0.25f,
            windowSize = VAD_WINDOW,
            maxSpeechDuration = 15f
        )
        val vadConfig = VadModelConfig(
            sileroVadModelConfig = sileroConfig,
            sampleRate = SAMPLE_RATE,
            numThreads = 1,
            debug = false
        )
        return Vad(context.assets, vadConfig)
    }

    fun startListening(context: Context) {
        if (_state.value == AsrState.Listening) return
        val rec = recognizer ?: run {
            _statusText.value = "Error: model not loaded"
            return
        }
        val vadInstance = vad ?: run {
            _statusText.value = "Error: VAD not loaded"
            return
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            _state.value = AsrState.Error("Microphone permission not granted")
            return
        }

        val minBufSize = AudioRecord.getMinBufferSize(
            SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT
        )
        if (minBufSize <= 0) {
            _state.value = AsrState.Error("16kHz mono audio not supported")
            return
        }
        val bufferSize = minBufSize.coerceAtLeast(SAMPLE_RATE * 2)

        audioRecord = createAudioRecord(MediaRecorder.AudioSource.VOICE_COMMUNICATION, bufferSize)
            ?: createAudioRecord(MediaRecorder.AudioSource.VOICE_RECOGNITION, bufferSize)
            ?: createAudioRecord(MediaRecorder.AudioSource.MIC, bufferSize)

        if (audioRecord == null) {
            _state.value = AsrState.Error("Failed to initialize microphone")
            return
        }

        enableAudioEffects(audioRecord!!.audioSessionId)
        vadInstance.reset()
        segmentChannel = Channel(capacity = 4)

        audioRecord?.startRecording()
        _state.value = AsrState.Listening
        _statusText.value = "Listening..."
        Log.i(TAG, "Started listening with VAD")

        // Capture loop: reads audio, feeds to VAD, sends speech segments to channel
        captureJob = scope.launch(Dispatchers.IO) {
            val readBuf = ShortArray(VAD_WINDOW)
            while (isActive) {
                val read = audioRecord?.read(readBuf, 0, readBuf.size) ?: break
                if (read <= 0) {
                    if (read < 0) Log.e(TAG, "AudioRecord.read error: $read")
                    break
                }

                val floats = FloatArray(read) { readBuf[it] / 32768.0f }
                vadInstance.acceptWaveform(floats)

                if (vadInstance.isSpeechDetected()) {
                    _statusText.value = "Hearing speech..."
                }

                // Send completed speech segments to the transcription loop
                while (!vadInstance.empty()) {
                    val segment = vadInstance.front()
                    vadInstance.pop()
                    val samples = segment.samples
                    Log.i(TAG, "Speech segment: ${samples.size} samples (${String.format("%.1f", samples.size.toFloat() / SAMPLE_RATE)}s)")
                    segmentChannel.trySend(samples)
                }
            }
        }

        // Transcription loop: receives speech segments and transcribes them
        transcribeJob = scope.launch(Dispatchers.IO) {
            for (samples in segmentChannel) {
                val durationSec = samples.size.toFloat() / SAMPLE_RATE
                _statusText.value = "Transcribing ${String.format("%.1f", durationSec)}s..."

                try {
                    val startMs = System.currentTimeMillis()
                    val text = transcribe(rec, samples)
                    val elapsed = System.currentTimeMillis() - startMs
                    Log.i(TAG, "Done in ${elapsed}ms: \"$text\"")

                    if (text.isNotBlank()) {
                        _finalResults.tryEmit(text)
                        _statusText.value = "Done (${elapsed}ms)"
                    } else {
                        _statusText.value = "No speech (${elapsed}ms)"
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Transcription failed", e)
                    _statusText.value = "Error: ${e.message}"
                }
            }
        }
    }

    private fun transcribe(rec: OfflineRecognizer, samples: FloatArray): String {
        val stream = rec.createStream()
        try {
            stream.acceptWaveform(samples, SAMPLE_RATE)
            rec.decode(stream)
            val result = rec.getResult(stream)
            return result.text.trim()
        } finally {
            stream.release()
        }
    }

    private fun enableAudioEffects(sessionId: Int) {
        if (android.media.audiofx.NoiseSuppressor.isAvailable()) {
            noiseSuppressor = android.media.audiofx.NoiseSuppressor.create(sessionId)
            noiseSuppressor?.enabled = true
            Log.i(TAG, "NoiseSuppressor: ${noiseSuppressor?.enabled}")
        }
        if (android.media.audiofx.AutomaticGainControl.isAvailable()) {
            gainControl = android.media.audiofx.AutomaticGainControl.create(sessionId)
            gainControl?.enabled = true
            Log.i(TAG, "AutomaticGainControl: ${gainControl?.enabled}")
        }
    }

    private fun releaseAudioEffects() {
        noiseSuppressor?.release()
        noiseSuppressor = null
        gainControl?.release()
        gainControl = null
    }

    private fun createAudioRecord(source: Int, bufferSize: Int): AudioRecord? {
        return try {
            val record = AudioRecord(
                source, SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize
            )
            if (record.state == AudioRecord.STATE_INITIALIZED) {
                Log.i(TAG, "AudioRecord OK: source=$source")
                record
            } else {
                record.release()
                null
            }
        } catch (e: Exception) {
            Log.w(TAG, "AudioRecord failed: source=$source, ${e.message}")
            null
        }
    }

    fun stopListening() {
        scope.launch {
            stopListeningInternal()
        }
    }

    private suspend fun stopListeningInternal() {
        try {
            captureJob?.cancelAndJoin()
            segmentChannel.close()  // ends transcribeJob's for-loop cleanly
            transcribeJob?.cancelAndJoin()
        } finally {
            captureJob = null
            transcribeJob = null
            releaseAudioEffects()
            audioRecord?.stop()
            audioRecord?.release()
            audioRecord = null
            vad?.reset()
            if (_state.value == AsrState.Listening) {
                _state.value = AsrState.Ready
            }
            _statusText.value = "Stopped"
        }
    }

    fun shutdown() {
        scope.launch {
            stopListeningInternal()
            recognizer?.release()
            recognizer = null
            vad?.release()
            vad = null
            _state.value = AsrState.Idle
            scope.cancel()
        }
    }
}
