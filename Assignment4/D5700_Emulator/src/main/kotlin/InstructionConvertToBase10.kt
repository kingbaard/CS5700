package org.example

class InstructionConvertToBase10 : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerOneRegistry()
    override fun performOperation(parameters: List<Int>) {
        val aRegistryValue = D5700Emulator.cpu.registers[0x41]?.getValueAsInt()

        // Convert rX to base 10
        val base10Value = D5700Emulator.cpu.registers[parameters[0]]?.getValueAsInt()
        
        // Seperate the digits of the base 10 value
        val digits = base10Value.toString().toCharArray()

        // Place the highest digit in ROM[rA], the second highest in ROM[rA+1], ect
        // Todo: Move the rom/ram check to a more relevant class
        for (i in 0 until digits.size) {
            if (D5700Emulator.cpu.registers[0x4D]?.getValueAsInt() == 0) {
                // Write to RAM
                if (aRegistryValue != null) {
                    D5700Emulator.ram.write(aRegistryValue + i, digits[i].toString().toUByte())
                }
            } else {
                // Attempt write to ROM
                if (aRegistryValue != null) {
                    D5700Emulator.rom.write(aRegistryValue + i, digits[i].toString().toUByte())
                }
            }
        }
    }
}