package org.example

class InstructionReadT : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerOneRegistry()

    override fun performOperation(parameters: List<Int>) {
        val timerValue = D5700Emulator.cpu.registers[0x54]?.getValueAsInt()

        if (timerValue != null) {
            D5700Emulator.cpu.registers[parameters[0]]?.setValue(timerValue)
        }
    }
}