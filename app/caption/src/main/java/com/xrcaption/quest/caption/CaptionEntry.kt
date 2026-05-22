package com.xrcaption.quest.caption

data class CaptionEntry(
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isFinal: Boolean = false
)
