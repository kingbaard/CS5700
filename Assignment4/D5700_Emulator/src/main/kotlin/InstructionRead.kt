package org.example

class InstructionRead : Instruction() {
    override val shouldIncrement: Boolean = true
    override val byteOrganizer: BytesOrganizer = OrganizerOneRegistry()
    override fun performOperation(parameters: List<Int>) {
        val addressValue = D5700Emulator.cpu.registers[0x41]?.getValueAsInt()
        val readValue : UByte
        if (addressValue != null) {
            readValue = D5700Emulator.currentMemoryDevice.read(addressValue)
            D5700Emulator.cpu.registers[parameters[0]]?.setValue(readValue)
        }
    }
}