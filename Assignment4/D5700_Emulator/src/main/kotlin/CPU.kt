package org.example

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.reflect.typeOf

@OptIn(ExperimentalUnsignedTypes::class)
class CPU {
    val registryFactory : RegistryFactory = RegistryFactory()
    val registers : MutableMap<Int, Register> = mutableMapOf()
    var instructionsMap : Map<Int, () -> Instruction> = mapOf(
        0x00 to {Store()},
        0x0F to {Draw()},

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

    fun tick() {
        // Read the two bytes
        // TODO: Better way of handling optionals here (try catch?)
        val instructionByte1 : UByte = D5700Emulator.rom.data[registers[0x50]?.data!!]
        val instructionByte2 : UByte = D5700Emulator.rom.data[registers[0x50]?.data!!+1]
        val currentInstructions : UByteArray = ubyteArrayOf(instructionByte1, instructionByte2)


    }

    fun executeInstructions() {
        val executor = Executors.newSingleThreadScheduledExecutor()
        val future = executor.scheduleAtFixedRate(Runnable {
            println("yo")
        },
            0,
            1000,
            TimeUnit.MICROSECONDS)

        Thread.sleep(5000)
        future.cancel(true)
    }

    fun executeInstruction() {

    }
}