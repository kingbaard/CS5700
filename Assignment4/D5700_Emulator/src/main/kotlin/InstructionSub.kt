package org.example

class InstructionSub : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerThreeRegisteries()

    override fun performOperation(parameters: List<Int>) {
        val xValue = D5700Emulator.cpu.registers[parameters[0]]?.getValueAsInt()
        val yValue = D5700Emulator.cpu.registers[parameters[1]]?.getValueAsInt()
        if (D5700Emulator.cpu.registers[parameters[3]] != null) {
            if (xValue != null && yValue != null) {
                D5700Emulator.cpu.registers[parameters[3]]?.setValue(xValue - yValue)
            }
        }
    }
}