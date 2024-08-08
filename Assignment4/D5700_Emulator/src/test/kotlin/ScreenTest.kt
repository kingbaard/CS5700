package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalUnsignedTypes::class)
class ScreenTest {

    private lateinit var screen: Screen

    @BeforeEach
    fun setUp() {
        screen = Screen()
    }

    @Test
    fun testInitialFrameBuffer() {
        val expected = UByteArray(64) { 0.toUByte() }
        assertEquals(expected[0], screen.frameBuffer[0])
        assertEquals(expected[10], screen.frameBuffer[10])
        assertEquals(expected[63], screen.frameBuffer[63])

    }

    @Test
    fun testClearFrameBuffer() {
        screen.setBufferCell(0, 0, 65) // Setting 'A' in first cell
        screen.clear()
        val expected = UByteArray(64) { 0.toUByte() }
        assertEquals(expected[0], screen.frameBuffer[0])
        assertEquals(expected[10], screen.frameBuffer[10])
        assertEquals(expected[63], screen.frameBuffer[63])
    }

    @Test
    fun testSetBufferCell() {
        screen.setBufferCell(1, 1, 65) // Setting 'A' in position (1, 1)
        assertEquals(65.toUByte(), screen.frameBuffer[9]) // 1*8 + 1 = 9
    }
}
