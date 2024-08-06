package org.example

class Sub : Instruction(), OrganizeBytesThreeRegisterParam {
    
    override fun performOperation(parameters: List<Int>) {
        val xValue = D5700Emulator.CPU.registers[parameters[0]].getValue()
        val yValue = D5700Emulator.CPU.registers[parameters[1]].getValue()

        D5700Emulator.CPU.registers[parameters[3]].setValue(xValue - yValue)
    }
}