package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class Draw : Instruction() {

    override fun organizeBytes(instructionBytes: UByteArray): List<Int> {
        val firstByte = instructionBytes[0]
        val secondByte = instructionBytes[1]

        val parameters: MutableList<Int> = mutableListOf()
        //rx
        parameters.add(getSecondNibble(firstByte))
        //ry
        parameters.add(getFirstNibble(secondByte))
        //rz
        parameters.add(getSecondNibble(secondByte))

        return parameters.toList()
    }

    override fun performOperation(parameters: List<UByte>) {
        val rx = parameters[0]
        val ry = parameters[1]
        val rz = parameters[2]
    }
}