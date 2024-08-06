package org.example

class Read : Instruction(), OrganizeByteOneRegisterParam {
    
    override fun performOperation(parameters: List<Int>) {
        val xValue = D5700Emulator.CPU.registers[parameters[0]].getValue()
        val yValue = D5700Emulator.CPU.registers[parameters[1]].getValue()

        D5700Emulator.CPU.registers[0x41].setValue(parameters[0])
    }
}