package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalUnsignedTypes::class)
class OneByteRegistryTest {

    private lateinit var registry: OneByteRegistry

    @BeforeEach
    fun setUp() {
        registry = OneByteRegistry()
    }

    @Test
    fun testInitialSize() {
        assertEquals(1, registry.size)
    }

    @Test
    fun testInitialData() {
        val expected = UByteArray(1) { 0.toUByte() }
        assertEquals(expected[0], (registry.data as RegistryDataType.UByteArray).value[0])
    }
}
