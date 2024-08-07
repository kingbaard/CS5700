package org.example

class InstructionSwitchMemory : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerNoParam ()
    override fun performOperation(parameters: List<Int>) {
        D5700Emulator.switchMemoryDevices()
    }
}