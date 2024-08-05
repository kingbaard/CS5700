package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class Screen {
    var frameBuffer: UByteArray = UByteArray(64) { 0.toUByte() }

    fun display() {
        // Prints frameBuffer
    }
}