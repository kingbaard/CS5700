package org.example

class InstructionSetT : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerOneByte()
    override fun performOperation(parameters: List<Int>) {


        D5700Emulator.cpu.registers[0x54]?.setValue(parameters[0])
    }
}