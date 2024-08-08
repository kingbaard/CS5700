package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@OptIn(ExperimentalUnsignedTypes::class)
class OneBitRegistryTest {

    private lateinit var registry: OneBitRegistry

    @BeforeEach
    fun setUp() {
        registry = OneBitRegistry()
    }

    @Test
    fun testInitialSize() {
        assertEquals(1, registry.size)
    }

    @Test
    fun testInitialData() {
        assertEquals(0, (registry.data as RegistryDataType.Integer).value)
    }

    @Test
    fun testSetValueWithInt() {
        registry.setValue(1)
        assertEquals(1, (registry.data as RegistryDataType.Integer).value)

        registry.setValue(0)
        assertEquals(0, (registry.data as RegistryDataType.Integer).value)
    }

    @Test
    fun testSetValueWithUByteArray() {
        registry.setValue(ubyteArrayOf(0.toUByte()))
        assertEquals(0, (registry.data as RegistryDataType.Integer).value)

        registry.setValue(ubyteArrayOf(1.toUByte()))
        assertEquals(1, (registry.data as RegistryDataType.Integer).value)

        registry.setValue(ubyteArrayOf(255.toUByte()))
        assertEquals(1, (registry.data as RegistryDataType.Integer).value)
    }

    @Test
    fun testSetValueWithInvalidSizeUByteArray() {
        val exception = assertThrows<IllegalArgumentException> {
            registry.setValue(ubyteArrayOf(1.toUByte(), 0.toUByte()))
        }
        assertEquals("Invalid size for OneBitRegistry", exception.message)
    }
}
