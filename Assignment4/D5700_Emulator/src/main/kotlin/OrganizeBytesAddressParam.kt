package org.example

@OptIn(ExperimentalUnsignedTypes::class)
interface OrganizeBytesAddressParam() : OrganizeBytesStrategy {
    fun organizeBytes(data: UByteArray): List<Int> {
        // Combines the second nibble of the first byte with the second byte
        val firstByteSecondNibble = getSecondNibble(data[0])
        val secondByteFirstNibble = getFirstNibble(data[1])
        val secondByteSecondNibble = getSecondNibble(data[1])

        return listOf((firstByteSecondNibble shl 8) or (secondByteFirstNibble shl 4) or secondByteSecondNibble)
    }
}