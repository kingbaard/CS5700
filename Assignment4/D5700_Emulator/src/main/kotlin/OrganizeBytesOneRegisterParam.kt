package org.example

@OptIn(ExperimentalUnsignedTypes::class)
interface OrganizeBytesOneRegisterParam() : OrganizeBytesStrategy {
    fun organizeBytes(data: UByteArray): List<Int> {
        // Each nibble (besides the first nibble of the first byte) is it's own parameter
        val firstByteSecondNibble: Int = getSecondNibble(data[0]).toInt()

        return listOf(firstByteSecondNibble)
    }
}