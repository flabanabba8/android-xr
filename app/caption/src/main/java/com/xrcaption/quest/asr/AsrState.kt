package com.xrcaption.quest.asr

sealed interface AsrState {
    data object Idle : AsrState
    data object Loading : AsrState
    data object Ready : AsrState
    data object Listening : AsrState
    data class Error(val message: String) : AsrState
}
