package org.example

class SetT : Instruction(), OrganizeByteOneByteParam {
    val shouldIncrement = true
    override fun performOperation(parameters: List<Int>) {
        val byteValue = parameters[0].toUByte()

        D5700Emulator.CPU.registers[0x54].setValue(byteValue)
    }
}