package org.example

import java.io.File
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

@OptIn(ExperimentalUnsignedTypes::class)
object D5700Emulator {
    var rom: ROM
    val ram: RAM
    var currentMemoryDevice: MemoryDevice
    val cpu: CPU = CPU()
    val screen: Screen = Screen()

    private val CPU_FREQUENCY_HZ = 500L
    private val TIMER_FREQUENCY_HZ = 60L

    private val executor = Executors.newSingleThreadScheduledExecutor()

    init {
        println("Emulator Init")
        rom = ROM(programPrompt())
        ram = RAM(null)
        currentMemoryDevice = ram
        runProgramAndTimer()
    }

    fun switchMemoryDevices() {
        cpu.registers[0x4D]?.getValueAsInt()?.xor(1)?.let { cpu.registers[0x4D]?.setValue(it) }
        currentMemoryDevice = if (currentMemoryDevice == ram) {
            rom
        } else {
            ram
        }
    }

    fun List<String>.toUByteArray(): UByteArray {
        return this.map { it.trim().toUByte(16) }.toUByteArray()
    }

    fun runProgramAndTimer() {
        val cpuRunnable = Runnable {
//            println("Running CPU Tick")
            try {
                cpu.runTick()
            } catch (e: Exception) {
                println("Error during CPU tick: ${e.message}")
                e.printStackTrace()
                shutdown()
            }
        }

        val timerRunnable = Runnable {
//            println("Running Timer Tick")
            try {
                cpu.decrementTimer()
            } catch (e: Exception) {
                println("Error during Timer tick: ${e.message}")
                e.printStackTrace()
            }
        }

        val cpuFuture = executor.scheduleAtFixedRate(
            cpuRunnable,
            0,
            1000L / CPU_FREQUENCY_HZ, // repeat frequency - every 2 ms (500 Hz)
            TimeUnit.MILLISECONDS
        )

        val timerFuture = executor.scheduleAtFixedRate(
            timerRunnable,
            0,
            1000L / TIMER_FREQUENCY_HZ, // repeat frequency - every 16 ms (60 Hz)
            TimeUnit.MILLISECONDS
        )

        // Properly stop executor
        Runtime.getRuntime().addShutdownHook(Thread {
            cpuFuture.cancel(true)
            timerFuture.cancel(true)
            executor.shutdown()
        })
    }

    fun shutdown() {
        println("Shutting down...")
        exitProcess(0)
    }

    private fun programPrompt(): UByteArray? {
        println("Enter the file path:")
        val filePath = readlnOrNull()

        if (filePath.isNullOrBlank()) {
            println("No file path entered.")
            return null
        }

        return try {
            val file = File(filePath)
            if (!file.exists() || !file.isFile) {
                println("The file does not exist or is not a file.")
                throw InstantiationError("Invalid path")
            } else {
                val byteArray = file.readBytes()
                byteArray.asUByteArray()
            }
        } catch (e: IOException) {
            println("An error occurred while reading the file: ${e.message}")
            null
        }
    }

    operator fun invoke() {
        println("Invoking Emulator")
    }
}
