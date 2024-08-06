package org.example

class Store : Instruction(), OrganizeByteOneRegisterOneByteParam {
    
    override fun performOperation(parameters: List<Int>) {
        D5700Emulator.CPU.registers[parameters[0]].setValue(parameters[1].toUByte())
    }
}