package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class OneBitRegistry : Registry() {
    override val size: Int = 1
    override var data : RegistryDataType = RegistryDataType.Integer(0)

    override fun setValue(value: Int) {
        this.data = RegistryDataType.Integer(value)
    }

    override fun setValue(value: UByteArray) {
        if (value.size != 1) {
            throw IllegalArgumentException("Invalid size for OneBitRegistry")
        }
        if (value[0] == 0.toUByte()) {
            this.data = RegistryDataType.Integer(0)
        } else {
            this.data = RegistryDataType.Integer(1)
        }
    }
}