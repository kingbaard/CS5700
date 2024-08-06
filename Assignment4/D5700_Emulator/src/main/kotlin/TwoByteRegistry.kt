package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class TwoByteRegistry : Register {
    override val size: Int = 2
    val data = UByteArray(size) { 0.toUByte() }


}