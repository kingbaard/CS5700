package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class Draw : Instruction(), OrganizeBytesThreeRegisterParam {
    val shouldIncrement = true

    override fun performOperation(parameters: List<Int>) {
        val rx = parameters[0].toChar()
        val ry = parameters[1]
        val rz = parameters[2]


        D5700Emulator.Screen.setBufferCell(D5700Emulator.CPU.registers[ry].getValue(), D5700Emulator.CPU.registers[rz].getValue(), D5700Emulator.CPU.registers[rx].getValue())
    }
}