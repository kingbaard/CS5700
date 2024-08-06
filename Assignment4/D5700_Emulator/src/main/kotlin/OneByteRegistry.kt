package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class OneByteRegistry : Register {
    override val size = 1
    override var data : RegisterDataType = RegisterDataType.UByteArray(UByteArray(size) { 0.toUByte() })

}