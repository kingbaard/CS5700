package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalUnsignedTypes::class)
class CPUTest {

    private lateinit var cpu: CPU
    private lateinit var ram: RAM
    private lateinit var rom: ROM

    @BeforeEach
    fun setUp() {
        ram = RAM(null)
        rom = ROM(null)
        cpu = CPU()
        cpu.registers[0x54]?.setValue(10)
    }

    @Test
    fun testInitialState() {
        assertNotNull(cpu)
        cpu.registers[0x00]?.let { assertEquals(0, it.getValueAsInt()) }
        cpu.registers[0x41]?.let { assertEquals(0, it.getValueAsInt()) }
        cpu.registers[0x50]?.let { assertEquals(0, it.getValueAsInt()) }

        // Add more assertions as needed for the initial state
    }

    @Test
    fun testTimerDecrement() {
        cpu.registers[0x54]?.let { assertEquals(10, it.getValueAsInt()) }
        cpu.decrementTimer()
        cpu.registers[0x54]?.let { assertEquals(9, it.getValueAsInt()) }
    }

    // Add more tests to cover other aspects of the CPU functionality
}
