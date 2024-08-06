package org.example

class SwitchMemory : Instruction(), OrganizeBytesNoParam {
    val shouldIncrement = false
    override fun performOperation(parameters: List<Int>) {
        D5700Emulator.CPU.registers[0x4D].setValue(D5700Emulator.CPU.registers[0x4D].getValue() xor 1)
    }
}