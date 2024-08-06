package org.example

class ConvertToBase10 : Instruction(), OrganizeBytesOneRegisterParam {
    val shouldIncrement = true
    override fun performOperation(parameters: List<Int>) {
        val aRegistryValue = D5700Emulator.CPU.registers[0x41].getValue()

        // Convert rX to base 10
        val base10Value = D5700Emulator.CPU.registers[parameters[0]].getValue().toInt(10)
        
        // Seperate the digits of the base 10 value
        val digits = base10Value.toString().toCharArray()

        // Place the highest digit in ROM[rA], the second highest in ROM[rA+1], ect
        for (i in 0 until digits.size) {
            if (D5700Emulator.CPU.registers[0x4D].getValue() == 0) {
                // Write to RAM
                D5700Emulator.RAM.write(aRegistryValue + i, digits[i].toString().toUByte())
            } else {
                // Attempt write to ROM
                D5700Emulator.ROM.write(aRegistryValue + i, digits[i].toString().toUByte())
            }
        }
    }
}