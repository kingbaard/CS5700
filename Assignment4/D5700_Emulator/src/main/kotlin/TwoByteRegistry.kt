package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class TwoByteRegistry : Register {
    override val size: Int = 2
    override var data : RegisterDataType = RegisterDataType.UByteArray(UByteArray(size) { 0.toUByte() })
}