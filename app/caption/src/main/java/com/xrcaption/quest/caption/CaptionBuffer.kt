package com.xrcaption.quest.caption

class CaptionBuffer(
    private val maxLines: Int = 4,
    private val maxAgeMs: Long = 30_000
) {
    private val entries = mutableListOf<CaptionEntry>()

    fun onPartialResult(text: String): List<CaptionEntry> {
        pruneOld()
        // Replace in-progress line (last non-final entry) or add new one
        val lastIndex = entries.indexOfLast { !it.isFinal }
        if (lastIndex >= 0) {
            entries[lastIndex] = CaptionEntry(text = text, isFinal = false)
        } else {
            entries.add(CaptionEntry(text = text, isFinal = false))
        }
        return snapshot()
    }

    fun onFinalResult(text: String): List<CaptionEntry> {
        pruneOld()
        // Replace in-progress line with final, or add new final
        val lastIndex = entries.indexOfLast { !it.isFinal }
        if (lastIndex >= 0) {
            entries[lastIndex] = CaptionEntry(text = text, isFinal = true)
        } else {
            entries.add(CaptionEntry(text = text, isFinal = true))
        }
        // Trim to maxLines
        while (entries.size > maxLines) {
            entries.removeFirst()
        }
        return snapshot()
    }

    fun clear(): List<CaptionEntry> {
        entries.clear()
        return emptyList()
    }

    private fun pruneOld() {
        val now = System.currentTimeMillis()
        entries.removeAll { it.isFinal && (now - it.timestamp) > maxAgeMs }
    }

    private fun snapshot(): List<CaptionEntry> = entries.toList()
}
