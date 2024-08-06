package org.example

class OneBitRegistry : Register {
    override val size: Int = 1
    override var data : RegisterDataType = RegisterDataType.Integer(0)
}