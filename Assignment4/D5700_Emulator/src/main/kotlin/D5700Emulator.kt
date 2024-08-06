package org.example

import java.io.File
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalUnsignedTypes::class)
object D5700Emulator {
    var rom: ROM
    val ram: RAM
    val cpu: CPU = CPU()
    val screen: Screen = Screen()

    private val CPU_FREQUENCY_HZ = 500L
    private val TIMER_FREQUENCY_HZ = 60L

    init {
        rom = ROM(programPrompt())
        ram = RAM(null)
        runProgram()
    }

    fun List<String>.toUByteArray(): UByteArray {
        return this.map { it.trim().toUByte(16) }.toUByteArray()
    }

    fun runProgram() {
        val executor = Executors.newSingleThreadScheduledExecutor()
        val cpuRunnable = Runnable {
            cpu.runTick()
        }

        val cpuFuture = executor.scheduleAtFixedRate(
            cpuRunnable,
            0,
            1000L / CPU_FREQUENCY_HZ, // repeat frequency - every 2 ms (500 Hz)
            TimeUnit.MILLISECONDS
        )

        // Properly stop executor
        Runtime.getRuntime().addShutdownHook(Thread {
            cpuFuture.cancel(true)
            executor.shutdown()
        })
    }

    fun runTimer() {
        val executor = Executors.newSingleThreadScheduledExecutor()
        val timerRunnable = Runnable {
            cpu.decrementTimer()
        }

        val timerFuture = executor.scheduleAtFixedRate(
            timerRunnable,
            0,
            1000L / TIMER_FREQUENCY_HZ, // repeat frequency - every 16 ms (60 Hz)
            TimeUnit.MILLISECONDS
        )

        // Properly stop executor
        Runtime.getRuntime().addShutdownHook(Thread {
            timerFuture.cancel(true)
            executor.shutdown()
        })
    }

    private fun shutdown() {
        println("Shutting down...")
        System.exit(0)
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
                null
            } else {
                file.readLines().toUByteArray()
            }
        } catch (e: IOException) {
            println("An error occurred while reading the file: ${e.message}")
            null
        }
    }
}