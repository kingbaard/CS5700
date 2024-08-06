package org.example

class Read : Instruction(), OrganizeBytesOneRegisterParam {
    val shouldIncrement = false
    override fun performOperation(parameters: List<Int>) {
    val scanner: Scanner = Scanner(System.`in`)
    val allowedChars: String= "0123456789ABCDEFabcdef"
    var input: String
    
    // Get input
    while (true) {
        print("Enter a hex digit (0-F), max 2 digits: ")
        input = scanner.nextLine().trim()

        if (input.length <= 2 && input.all { it in allowedChars }) {
            return input.toUpperCase()
        } else {
            println("Invalid input. Please enter up to 2 base-16 digits (0-9, A-F).")
        }
    }

    // Convert to byte
    if (input == '') {
        val inputByte = 0.toUByte()
    } else {
        val inputByte = input.toUByte(16)
    }

    // Write to register
    D5700Emulator.CPU.registers[parameters[0]].setValue(inputByte)
    
}
}