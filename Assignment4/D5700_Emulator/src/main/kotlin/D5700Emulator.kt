package org.example

import java.io.File
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalUnsignedTypes::class)
object D5700Emulator {
    var rom: ROM
    val ram: RAM = RAM(null)
    val cpu: CPU = CPU()
    val screen: Screen = Screen()

    init {
        rom = ROM(programPrompt())
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
            1000L / 500L, // repeat frequency - every 2 ms (500 Hz)
            TimeUnit.MILLISECONDS
        )

        // Shutdown executor gracefully when needed
        Runtime.getRuntime().addShutdownHook(Thread {
            cpuFuture.cancel(true)
            executor.shutdown()
        })
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