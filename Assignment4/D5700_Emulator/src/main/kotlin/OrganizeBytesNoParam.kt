package org.example

@OptIn(ExperimentalUnsignedTypes::class)
interface OrganizeBytesNoParam() : OrganizeBytesStrategy {
    fun organizeBytes(data: UByteArray): List<Int> {
        return listOf<Int>()
    }
}