package org.example

@OptIn(ExperimentalUnsignedTypes::class)
interface OrganizeByteOneByteParam() : OrganizeBytesStrategy {
    fun organizeBytes(data: UByteArray): List<Int> {
        // Each nibble (besides the first nibble of the first byte) is it's own parameter
        val firstByteSecondNibble: Int = getSecondNibble(data[0])
        val secondByteFirstNibble = getFirstNibble(data[1])

        return listOf((firstByteSecondNibble shl 4) or secondByteFirstNibble)
    }
}