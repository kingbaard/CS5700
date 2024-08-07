package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class OrganizerOneByte: BytesOrganizer() {
    override fun organizeBytes(data: UByteArray): List<Int> {
        // Byte is made up of second half of first byte and first half of second byte
        val firstByteSecondNibble: Int = getSecondNibble(data[0])
        val secondByteFirstNibble = getFirstNibble(data[1])
        val intValue = ((firstByteSecondNibble shl 4) or secondByteFirstNibble).toInt()
        return listOf((firstByteSecondNibble shl 4) or secondByteFirstNibble)
    }
}