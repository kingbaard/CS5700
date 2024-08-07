package org.example

@OptIn(ExperimentalUnsignedTypes::class)
abstract class BytesOrganizer {
    abstract fun organizeBytes(data: UByteArray): List<Int>

    fun getFirstNibble(byte : UByte): Int {
        return byte.toInt() shr 4
    }

    fun getSecondNibble(byte : UByte): Int {
        return byte.toInt() and 0xF
    }
}