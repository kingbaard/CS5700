package org.example

@OptIn(ExperimentalUnsignedTypes::class)
abstract class Instruction {
    val shouldIncrement: Boolean = true
    // primitive methods
    abstract fun organizeBytes(instructionBytes: UByteArray) : List<Int>

    fun getFirstNibble(byte : UByte): Int {
        return byte.toInt() shr 4
    }

    fun getSecondNibble(byte : UByte): Int {
        return byte.toInt() and 0xF
    }

    abstract fun performOperation(parameters: List<UByte>)
}