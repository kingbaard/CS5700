package org.example

class InstructionSetA : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerOneAddress()
    override fun performOperation(parameters: List<Int>) {
        val addressValue = parameters[0].toUByte()

        D5700Emulator.cpu.registers[0x41]?.setValue(addressValue)
    }
}