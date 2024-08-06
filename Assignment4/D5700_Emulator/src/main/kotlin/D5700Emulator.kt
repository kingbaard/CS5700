package org.example

import java.io.File
import java.io.IOException

@OptIn(ExperimentalUnsignedTypes::class)
object D5700Emulator {
    var rom: ROM
    val ram: RAM = RAM(null)
    val cpu: CPU = CPU()
    val screen: Screen = Screen()

    init {
        rom = ROM(programPrompt())
    }

    fun List<String>.toUByteArray(): UByteArray {
        return this.map { it.trim().toUByte(16) }.toUByteArray()
    }

    fun runProgram() {

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