package org.example

import java.util.*

class InstructionReadKeyboard : Instruction() {
    override val shouldIncrement = true
    override val byteOrganizer: BytesOrganizer = OrganizerOneRegistry()
    override fun performOperation(parameters: List<Int>) {
        val scanner: Scanner = Scanner(System.`in`)
        val allowedChars: String= "0123456789ABCDEFabcdef"
        var input: String = ""
        var isValidInput = false
        // Get input
        while (!isValidInput) {
            print("Enter a hex digit (0-F), max 2 digits: ")
            input = scanner.nextLine().trim()

            if (input.length <= 2 && input.all { it in allowedChars }) {
                input.uppercase()
                isValidInput = true
            } else {
                println("Invalid input. Please enter up to 2 base-16 digits (0-9, A-F).")
            }
        }

        // Convert to byte
        var inputByte = 0.toUByte()
        if (input.isNotEmpty()) {
            inputByte = input.toUByte(16)
        }

        // Write to register
        D5700Emulator.cpu.registers[parameters[0]]?.setValue(inputByte.toInt())
        println("registry ${parameters[0]} value: ${D5700Emulator.cpu.registers[parameters[0]]?.getValueAsInt()}")
    }
}