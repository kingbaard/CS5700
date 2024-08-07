package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class OrganizerNoParam : BytesOrganizer() {
    override fun organizeBytes(data: UByteArray): List<Int> {
        return listOf<Int>()
    }
}