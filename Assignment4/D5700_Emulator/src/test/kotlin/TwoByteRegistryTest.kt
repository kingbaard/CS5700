package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalUnsignedTypes::class)
class TwoByteRegistryTest {

    private lateinit var registry: TwoByteRegistry

    @BeforeEach
    fun setUp() {
        registry = TwoByteRegistry()
    }

    @Test
    fun testInitialSize() {
        assertEquals(2, registry.size)
    }

    @Test
    fun testInitialData() {
        val expected = UByteArray(2) { 0.toUByte() }
        assertEquals(expected[0], (registry.data as RegistryDataType.UByteArray).value[0])
        assertEquals(expected[1], (registry.data as RegistryDataType.UByteArray).value[1])
    }

    @Test
    fun testSetValueWithInt() {
        registry.setValue(256) // 256 -> [1, 0]
        val expected = ubyteArrayOf(1.toUByte(), 0.toUByte())
        assertEquals(expected[0], (registry.data as RegistryDataType.UByteArray).value[0])
        assertEquals(expected[1], (registry.data as RegistryDataType.UByteArray).value[1])

        registry.setValue(65535) // 65535 -> [255, 255]
        val expectedMax = ubyteArrayOf(255.toUByte(), 255.toUByte())
        assertEquals(expectedMax[0], (registry.data as RegistryDataType.UByteArray).value[0])
        assertEquals(expectedMax[1], (registry.data as RegistryDataType.UByteArray).value[1])
    }
}
