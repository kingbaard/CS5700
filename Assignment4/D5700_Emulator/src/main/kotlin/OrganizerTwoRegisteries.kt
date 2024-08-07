package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class OrganizerTwoRegistries : BytesOrganizer() {
    override fun organizeBytes(data: UByteArray): List<Int> {
        // 
        val firstByteSecondNibble: Int = getSecondNibble(data[0]).toInt()
        val secondByteFirstNibble: Int = getFirstNibble(data[1]).toInt()

        return listOf<Int>(firstByteSecondNibble, secondByteFirstNibble)
    }
}