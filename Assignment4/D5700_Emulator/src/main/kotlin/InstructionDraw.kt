package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class InstructionDraw : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerThreeRegisteries()

    override fun performOperation(parameters: List<Int>) {
        val rx = parameters[0]
        val ry = parameters[1]
        val rz = parameters[2]

        val rxRegister = D5700Emulator.cpu.registers[rx]
        val ryRegister = D5700Emulator.cpu.registers[ry]
        val rzRegister = D5700Emulator.cpu.registers[rz]

        if (rxRegister == null || ryRegister == null || rzRegister == null) {
            throw Error("Invalid Register")
        } else {
            D5700Emulator.screen.setBufferCell(
                ry,
                rx,
                rzRegister.getValueAsInt()
            )
        }
        D5700Emulator.screen.display()
    }
}