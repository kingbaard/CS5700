package org.example

class InstructionWrite : Instruction() {
    override val shouldIncrement: Boolean = true
    override val byteOrganizer = OrganizerOneRegistry()

    override fun performOperation(parameters: List<Int>) {
        val addressIndex : Int = D5700Emulator.cpu.registers[0x41]?.getValueAsInt() ?: throw Error()
        val rxValue = D5700Emulator.cpu.registers[parameters[0]]?.getValueAsInt()

        if (rxValue != null) {
            D5700Emulator.currentMemoryDevice.write(addressIndex, rxValue.toUByte())
        }
    }

}