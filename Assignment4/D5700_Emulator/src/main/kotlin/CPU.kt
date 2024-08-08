package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class CPU {
    private val registryFactory : RegistryFactory = RegistryFactory()
    val registers : MutableMap<Int, Registry> = mutableMapOf()

    private var instructionsMap : Map<Int, Instruction> = mapOf(
            0x00 to InstructionStore(),
            0x01 to InstructionAdd(),
            0x02 to InstructionSub(),
            0x03 to InstructionRead(),
            0x04 to InstructionWrite(),
            0x05 to InstructionJump(),
            0x06 to InstructionReadKeyboard(),
            0x07 to InstructionSwitchMemory(),
            0x08 to InstructionSkipEqual(),
            0x09 to InstructionSkipNotEqual(),
            0x0A to InstructionSetA(),
            0x0B to InstructionSetT(),
            0x0C to InstructionReadT(),
            0x0D to InstructionConvertToBase10(),
            0x0E to InstructionConvertByteToAscii(),
            0x0F to InstructionDraw(),
        )

    init {
        for (i in 0..7) {
            registers[i] = registryFactory.createRegistry(RegistryType.ONE_BYTE)
        }
        registers[0x50] = registryFactory.createRegistry(RegistryType.TWO_BYTE) // P rogram Counter
        registers[0x54] = registryFactory.createRegistry(RegistryType.ONE_BYTE) // T imer
        registers[0x41] = registryFactory.createRegistry(RegistryType.TWO_BYTE) // A ddress
        registers[0x4D] = registryFactory.createRegistry(RegistryType.ONE_BIT) // M emory
    }

    fun runTick() {
        val pcRegister = registers[0x50] ?:throw IllegalStateException("Program counter registery not initialized")
        val pcRegisterValue = pcRegister.getValueAsInt()
        // Read the two bytes
        val instructionByte1 : UByte = D5700Emulator.rom.data[pcRegisterValue]
        val instructionByte2 : UByte = D5700Emulator.rom.data[pcRegisterValue+1]
        val currentInstructions : UByteArray = ubyteArrayOf(instructionByte1, instructionByte2)

        // Run current instructions
        executeInstruction(currentInstructions, pcRegister)
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

    fun executeInstruction(stepByes: UByteArray, pcRegister: Registry) {
        val instructionParameter = stepByes[0].toInt() shr 4
        val parsedInstruction : Instruction? = instructionsMap[instructionParameter]
        if (parsedInstruction != null ) {
            parsedInstruction.execute(stepByes, pcRegister)
        } else {
            throw InternalError("Invalid registery nibble.")
        }
    }
}