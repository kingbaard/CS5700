package org.example

class Read : Instruction(), OrganizeBytesAddressParam {
    val shouldIncrement = false
    override fun performOperation(parameters: List<Int>) {
        val address = parameters[0]
        if (address < 0 || address >= D5700Emulator.CPU.memory.size) {
            throw IllegalArgumentException("Read Error: Address out of bounds")
        }
        if (address % 2 != 0) {
            throw IllegalArgumentException("Read Error: Address must be even")
        }

        D5700Emulator.CPU.registers[0x41].setValue(parameters[0])
    }
}