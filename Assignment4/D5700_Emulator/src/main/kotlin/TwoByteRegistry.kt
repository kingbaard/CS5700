package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class TwoByteRegistry : Registry() {
    override val size: Int = 2
    override var data : RegistryDataType = RegistryDataType.UByteArray(UByteArray(size) { 0.toUByte() })

    override fun setValue(value: Int) {
        val byteArray = UByteArray(size)
        byteArray[0] = (value shr 8).toUByte()
        byteArray[1] = value.toUByte()
        this.data = RegistryDataType.UByteArray(byteArray)
    }
}