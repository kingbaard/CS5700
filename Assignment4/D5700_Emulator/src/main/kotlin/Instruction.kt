package org.example

@OptIn(ExperimentalUnsignedTypes::class)
abstract class Instruction {
    abstract val shouldIncrement: Boolean
    // primitive methods
    abstract fun organizeBytes(instructionBytes: UByteArray) : List<Int>

    // fun getFirstNibble(byte : UByte): Int {
    //     return byte.toInt() shr 4
    // }

    // fun getSecondNibble(byte : UByte): Int {
    //     return byte.toInt() and 0xF
    // }

    abstract fun performOperation(parameters: List<Int>)

    fun execute(instructionBytes: UByteArray, programCounter: Register) {
        val parameters = organizeBytes(instructionBytes)
        performOperation(parameters)
        incrementPC(programCounter)
    }

    fun incrementPC(programCounter: Register): {
        if (shouldIncrement) {
            programCounter.setValue(programCounter.data + 2)
        } 
    }
}