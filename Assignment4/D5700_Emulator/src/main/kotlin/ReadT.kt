package org.example

class ReadT : Instruction(), OrganizeBytesOneRegisterParam {
    val shouldIncrement = true
    override fun performOperation(parameters: List<Int>) {
        val timerValue = D5700Emulator.CPU.registers[0x54].getValue()

        D5700Emulator.CPU.registers[parameters[0]].setValue(timerValue)
    }
}