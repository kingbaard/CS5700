package org.example

class SetA : Instruction(), OrganizeBytesAddressParam {
    val shouldIncrement = true
    override fun performOperation(parameters: List<Int>) {
        val addressValue = parameters[0].toUByte()

        D5700Emulator.CPU.registers[0x41].setValue(addressValue)
    }
}