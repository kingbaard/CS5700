package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class OrganizerThreeRegisteries : BytesOrganizer() {
    override fun organizeBytes(data: UByteArray): List<Int> {
        // Each nibble (besides the first nibble of the first byte) is its own parameter
        val firstByteSecondNibble: Int = getSecondNibble(data[0]).toInt()
        val secondByteFirstNibble: Int = getFirstNibble(data[1]).toInt()
        val secondByteSecondNibble: Int = getSecondNibble(data[1]).toInt()

        return listOf(firstByteSecondNibble, secondByteFirstNibble, secondByteSecondNibble)
    }
}