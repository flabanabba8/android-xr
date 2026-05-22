package com.xrcaption.quest.caption

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CaptionBufferTest {

    @Test
    fun `partial result creates non-final entry`() {
        val buffer = CaptionBuffer()
        val result = buffer.onPartialResult("hello")
        assertEquals(1, result.size)
        assertEquals("hello", result[0].text)
        assertFalse(result[0].isFinal)
    }

    @Test
    fun `subsequent partials replace in-progress entry`() {
        val buffer = CaptionBuffer()
        buffer.onPartialResult("hel")
        buffer.onPartialResult("hello")
        val result = buffer.onPartialResult("hello world")
        assertEquals(1, result.size)
        assertEquals("hello world", result[0].text)
    }

    @Test
    fun `final result marks entry as final`() {
        val buffer = CaptionBuffer()
        buffer.onPartialResult("hello")
        val result = buffer.onFinalResult("hello world")
        assertEquals(1, result.size)
        assertEquals("hello world", result[0].text)
        assertTrue(result[0].isFinal)
    }

    @Test
    fun `new partial after final creates second entry`() {
        val buffer = CaptionBuffer()
        buffer.onFinalResult("first sentence")
        val result = buffer.onPartialResult("second")
        assertEquals(2, result.size)
        assertTrue(result[0].isFinal)
        assertFalse(result[1].isFinal)
    }

    @Test
    fun `buffer trims to max lines on final`() {
        val buffer = CaptionBuffer(maxLines = 2)
        buffer.onFinalResult("one")
        buffer.onFinalResult("two")
        val result = buffer.onFinalResult("three")
        assertEquals(2, result.size)
        assertEquals("two", result[0].text)
        assertEquals("three", result[1].text)
    }

    @Test
    fun `clear empties buffer`() {
        val buffer = CaptionBuffer()
        buffer.onFinalResult("text")
        val result = buffer.clear()
        assertTrue(result.isEmpty())
    }
}
