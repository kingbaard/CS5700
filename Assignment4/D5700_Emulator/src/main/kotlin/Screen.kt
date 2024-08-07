package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class Screen {
    var frameBuffer: UByteArray = UByteArray(64) { 0.toUByte() }

    fun display() {
        // Prints frameBuffer
        for (i in 0 until 64) {
            val asciiValue = frameBuffer[i].toInt().toChar()
            print("$asciiValue ")
            if (i % 8 == 7) {
                println()
            }
        }
        println("===============")
    }

    fun clear() {
        frameBuffer = UByteArray(64) { 0.toUByte() }
    }

    fun setBufferCell(row: Int, col: Int, value: Int) {
        frameBuffer[row * 8 + col] = value.toUByte()
    }
}