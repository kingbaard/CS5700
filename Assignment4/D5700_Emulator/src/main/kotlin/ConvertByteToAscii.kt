package org.example

class ConvertByteToAscii : Instruction(), OrganizeBytesTwoRegisterParam {
    val shouldIncrement = true
    override fun performOperation(parameters: List<Int>) {
        val xValue = D5700Emulator.CPU.registers[parameters[0]].getValue()
        val asciiValue = xValue.toChar()

        D5700Emulator.CPU.registers[parameters[1]].setValue(asciiValue.toUByte())
    }
}