package org.example

@OptIn(ExperimentalUnsignedTypes::class)
interface OrganizeBytesTwoParam() : OrganizeBytesStrategy {
    fun organizeBytes(data: UByteArray): List<Int> {
        // 
        val firstByteSecondNibble = getSecondNibble(data[0])
        val secondByteFirstNibble = getFirstNibble(data[1])
        val secondByteSecondNibble = getSecondNibble(data[1])

        return (firstByteSecondNibble shl 8) or (secondByteFirstNibble shl 4) or secondByteSecondNibble
    }
}