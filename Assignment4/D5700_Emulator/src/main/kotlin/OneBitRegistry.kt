package org.example

class OneBitRegistry : Register {
    override val size: Int = 1
    override var data : RegisterDataType = RegisterDataType.Integer(0)

    override fun setValue(value: Int) {
        this.data = RegisterDataType.Integer(value)
    }

    overide fun setValue(value: UByteArray) {
        if (value.size != 1) {
            throw IllegalArgumentException("Invalid size for OneBitRegistry")
        }
        if (value[0] == 0.toUByte()) {
            this.data = RegisterDataType.Integer(0)
        } else {
            this.data = RegisterDataType.Integer(1)
        }
    }
}