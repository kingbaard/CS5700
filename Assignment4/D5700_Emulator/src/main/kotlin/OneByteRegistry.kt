package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class OneByteRegistry : Register {
    override val size = 1
    val data = UByteArray(size) { 0.toUByte() }

}