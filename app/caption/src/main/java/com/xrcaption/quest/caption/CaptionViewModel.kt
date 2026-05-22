package com.xrcaption.quest.caption

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xrcaption.quest.asr.AsrState
import com.xrcaption.quest.asr.VoskEngine
import com.xrcaption.quest.settings.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CaptionUiState(
    val asrState: AsrState = AsrState.Idle,
    val captions: List<CaptionEntry> = emptyList(),
    val fontSize: Int = 24
)

class CaptionViewModel(application: Application) : AndroidViewModel(application) {

    private val engine = VoskEngine()
    private val buffer = CaptionBuffer()
    private val prefs = UserPreferences(application)

    private val _uiState = MutableStateFlow(CaptionUiState())
    val uiState: StateFlow<CaptionUiState> = _uiState

    init {
        viewModelScope.launch {
            engine.loadModel(getApplication())
        }
        viewModelScope.launch {
            engine.state.collect { state ->
                _uiState.update { it.copy(asrState = state) }
            }
        }
        viewModelScope.launch {
            engine.partialResults.collect { text ->
                val captions = buffer.onPartialResult(text)
                _uiState.update { it.copy(captions = captions) }
            }
        }
        viewModelScope.launch {
            engine.finalResults.collect { text ->
                val captions = buffer.onFinalResult(text)
                _uiState.update { it.copy(captions = captions) }
            }
        }
        viewModelScope.launch {
            prefs.fontSize.collect { size ->
                _uiState.update { it.copy(fontSize = size) }
            }
        }
    }

    fun toggleListening() {
        when (_uiState.value.asrState) {
            is AsrState.Ready -> engine.startListening()
            is AsrState.Listening -> engine.stopListening()
            else -> {}
        }
    }

    fun increaseFontSize() {
        val newSize = (_uiState.value.fontSize + 2).coerceAtMost(40)
        viewModelScope.launch { prefs.setFontSize(newSize) }
    }

    fun decreaseFontSize() {
        val newSize = (_uiState.value.fontSize - 2).coerceAtLeast(18)
        viewModelScope.launch { prefs.setFontSize(newSize) }
    }

    fun clearCaptions() {
        val captions = buffer.clear()
        _uiState.update { it.copy(captions = captions) }
    }

    override fun onCleared() {
        super.onCleared()
        engine.shutdown()
    }
}
