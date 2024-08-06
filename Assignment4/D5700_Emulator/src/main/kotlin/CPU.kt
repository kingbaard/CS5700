package org.example

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.reflect.typeOf

@OptIn(ExperimentalUnsignedTypes::class)
class CPU {
    val registryFactory : RegistryFactory = RegistryFactory()
    val registers : MutableMap<Int, Register> = mutableMapOf()
//    var instructionsMap : Map<Int, () -> Instruction> = mapOf(
//        0x00 to {Store()},
//        0x0F to {Draw()},
    var instructionsMap : Map<Int, Instruction> = mapOf(
        0x00 to Store(),
        0x01 to Add(),
        0x02 to Sub(),
        0x03 to Read(),
        0x04 to Write(),
        0x05 to Jump(),
        0x06 to ReadKeyboard(),
        0x07 to SwitchMemory(),
        0x08 to SkipEqual(),
        0x09 to SkipNotEqual(),
        0x0A to SetA(),
        0x0B to SetT(),
        0x0C to ReadT(),
        0x0D to ConvertToBase10(),
        0x0E to ConvertByteToAscii(),
        0x0F to Draw(),
    )

    init {
        for (i in 0..7) {
            registers[i] = registryFactory.createRegistry(RegisterType.ONE_BYTE)
        }
        registers[0x50] = registryFactory.createRegistry(RegisterType.TWO_BYTE) // P rogram Counter
        registers[0x54] = registryFactory.createRegistry(RegisterType.ONE_BYTE) // T imer
        registers[0x41] = registryFactory.createRegistry(RegisterType.TWO_BYTE) // A ddress
        registers[0x4D] = registryFactory.createRegistry(RegisterType.ONE_BIT) // M emory
    }

    fun runTick() {

        val pcRegister = registers[0x50] ?:throw IllegalStateException("Program counter not initialized")
        val pcRegisterValue = pcRegister.getValueAsInt()
        // Read the two bytes
        val instructionByte1 : UByte = D5700Emulator.rom.data[pcRegisterValue]
        val instructionByte2 : UByte = D5700Emulator.rom.data[pcRegisterValue+1]
        val currentInstructions : UByteArray = ubyteArrayOf(instructionByte1, instructionByte2)

        // Run current instructions
        executeInstruction(currentInstructions)
    }

    fun decrementTimer() {
        try {
            val timerRegister = registers[0x54] ?: throw IllegalStateException("Timer register not initialized")
            val timerValue = timerRegister.getValueAsInt()
            if (timerValue > 0) {
                timerRegister.setValue(timerValue - 1)
            }
        } catch (e: Exception) {
            println("Error during decrementTimer: ${e.message}")
        }
    }

    fun executeInstruction(instructions: UByteArray) {
        val instructionParameter = instructions[0].toInt() shr 4
        val parsedInstruction : Instruction? = instructionsMap[instructionParameter]
        if (parsedInstruction != null ) {
            val organizedParameters = parsedInstruction.organizeBytes(instructions)
            parsedInstruction.performOperation(organizedParameters)
        } else {
            throw InternalError("Invalid registery nibble.")
        }
    }

    fun intToTwoBytes(value: Int): RegisterDataType.UByteArray {
        require(value in 0..0xFFFF) { "Value must be a 16-bit integer." }
        val highByte : UByte = (value shr 8).toUByte()
        val lowByte : UByte = value.toUByte()
        return RegisterDataType.UByteArray(ubyteArrayOf(highByte, lowByte))
    }
}