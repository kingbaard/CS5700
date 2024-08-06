package org.example

@OptIn(ExperimentalUnsignedTypes::class)
interface OrganizeBytesThreeParam() : OrganizeBytesStrategy {
    fun organizeBytes(data: UByteArray): List<Int> {
        return data
    }
}