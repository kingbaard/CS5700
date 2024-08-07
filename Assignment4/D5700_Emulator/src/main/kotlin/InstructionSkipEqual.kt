package org.example

class InstructionSkipEqual : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerTwoRegistries()
    override fun performOperation(parameters: List<Int>) {
        val xValue = D5700Emulator.cpu.registers[parameters[0]]?.getValueAsInt()
        val yValue = D5700Emulator.cpu.registers[parameters[1]]?.getValueAsInt()

        // Todo: get rid of !!
        if (xValue == yValue) {
            D5700Emulator.cpu.registers[0x50]?.setValue(D5700Emulator.cpu.registers[0x50]!!.getValueAsInt() + 2)
        }
    }
}