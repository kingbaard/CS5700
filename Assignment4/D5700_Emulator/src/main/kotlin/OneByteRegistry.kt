package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class OneByteRegistry : Registry() {
    override val size = 1
    override var data : RegistryDataType = RegistryDataType.UByteArray(UByteArray(size) { 0.toUByte() })

}