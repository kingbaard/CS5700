package org.example

@OptIn(ExperimentalUnsignedTypes::class)
interface OrganizeBytesThreeRegisterParam() : OrganizeBytesStrategy {
    fun organizeBytes(data: UByteArray): List<Int> {
        // Each nibble (besides the first nibble of the first byte) is it's own parameter
        val firstByteSecondNibble: Int = getSecondNibble(data[0]).toInt()
        val secondByteFirstNibble: Int = getFirstNibble(data[1]).toInt()
        val secondByteSecondNibble: Int = getSecondNibble(data[1]).toInt()

        return listOf(firstByteSecondNibble, secondByteFirstNibble, secondByteSecondNibble)
    }
}