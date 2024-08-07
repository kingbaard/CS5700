package org.example

class InstructionStore : Instruction() {
    override val shouldIncrement: Boolean = true
    override val byteOrganizer = OrganizerOneRegisterOneByte()

    override fun performOperation(parameters: List<Int>) {
        //If 0000 is encountered, end program
        if (parameters[0] == 0 && parameters[1] == 0) {
            println("End of program reached.")
            D5700Emulator.shutdown()
        }
        D5700Emulator.cpu.registers[parameters[0]]?.setValue(parameters[1])
    }

}