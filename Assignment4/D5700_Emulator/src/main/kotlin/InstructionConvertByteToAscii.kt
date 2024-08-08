package org.example

class InstructionConvertByteToAscii : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerTwoRegistries()

    override fun performOperation(parameters: List<Int>) {
        val xValue = D5700Emulator.cpu.registers[parameters[0]]?.getValueAsInt()
        val asciiValue = (xValue?.plus('0'.toInt()))?.toUByte()

        // Todo: Fix this
        if (asciiValue != null) {
            D5700Emulator.cpu.registers[parameters[1]]?.setValue(asciiValue)
        }
    }
}