package org.example

@OptIn(ExperimentalUnsignedTypes::class)
abstract class Instruction {
    abstract val shouldIncrement: Boolean
    abstract val byteOrganizer: BytesOrganizer

    abstract fun performOperation(parameters: List<Int>)

    fun execute(instructionBytes: UByteArray, programCounter: Registry) {
        val parameters = byteOrganizer.organizeBytes(instructionBytes)
        performOperation(parameters)
        incrementPC(programCounter)
    }

    fun incrementPC(programCounter: Registry) {
        if (shouldIncrement) {
            programCounter.setValue(programCounter.getValueAsInt() + 2)
        } 
    }
}