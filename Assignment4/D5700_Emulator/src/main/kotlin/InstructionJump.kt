package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class InstructionJump : Instruction() {
    override val shouldIncrement = false
    override val byteOrganizer: BytesOrganizer = OrganizerOneAddress()
    override fun performOperation(parameters: List<Int>) {
        val address = parameters[0]
        // todo: add upperbound to check
//        if (address < 0 || address >= D5700Emulator.currentMemoryDevice.size) {
        if (address < 0 || address > D5700Emulator.rom.data.size) {
            throw IllegalArgumentException("Read Error: Address out of bounds")
        }
        if (address % 2 != 0) {
            throw IllegalArgumentException("Read Error: Address must be even")
        }

        D5700Emulator.cpu.registers[0x50]?.setValue(parameters[0])
    }
}