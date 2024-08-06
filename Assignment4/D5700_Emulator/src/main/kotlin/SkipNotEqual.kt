package org.example

class SkipNotEqual : Instruction(), OrganizeBytesTwoRegisterParam {
    val shouldIncrement = false
    override fun performOperation(parameters: List<Int>) {
        val xValue = D5700Emulator.CPU.registers[parameters[0]].getValue()
        val yValue = D5700Emulator.CPU.registers[parameters[1]].getValue()

        if (xValue != yValue) {
            D5700Emulator.CPU.registers[0x50].setValue(D5700Emulator.CPU.registers[0x4D].getValue() + 2)
        }
    }
}