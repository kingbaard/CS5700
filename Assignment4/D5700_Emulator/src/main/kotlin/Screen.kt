package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class Screen {
    var frameBuffer: UByteArray = UByteArray(64) { 0.toUByte() }

    fun display() {
        // Prints frameBuffer
        for (i in 0 until 64) {
            print("${frameBuffer[i]} ")
            if (i % 8 == 7) {
                println()
            }
        }
    }

    fun clear() {
        frameBuffer = UByteArray(64) { 0.toUByte() }
    }

    fun setBufferCell(row: Int, col: Int, value: UByte) {
        frameBuffer[row * 8 + col] = value
    }
}