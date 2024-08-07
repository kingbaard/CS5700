import org.example.MemoryDevice
import org.example.RAM
import org.example.ROM
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

class MemoryDeviceTest {

    private lateinit var ram: RAM
    private lateinit var rom: ROM
    private lateinit var memoryDevice: MemoryDevice

    @BeforeEach
    fun setUp() {
        ram = RAM(4096)
        val romData = ByteArray(4096) { it.toByte() }
        rom = ROM(romData)
        memoryDevice = MemoryDevice(ram, rom)
    }

    @Test
    fun testReadFromRAM() {
        memoryDevice.setMemoryType(MemoryType.RAM)
        ram.write(0, 0xAA)
        assertEquals(0xAA, memoryDevice.read(0))
    }

    @Test
    fun testReadFromROM() {
        memoryDevice.setMemoryType(MemoryType.ROM)
        assertEquals(0x00, memoryDevice.read(0))
        assertEquals(0xFF, memoryDevice.read(255).toInt() and 0xFF)
    }

    @Test
    fun testWriteToRAM() {
        memoryDevice.setMemoryType(MemoryType.RAM)
        memoryDevice.write(0, 0xAA)
        assertEquals(0xAA, ram.read(0))
    }

    @Test
    fun testWriteToROMShouldFail() {
        memoryDevice.setMemoryType(MemoryType.ROM)
        assertThrows(UnsupportedOperationException::class.java) {
            memoryDevice.write(0, 0xFF)
        }
    }

    @Test
    fun testSwitchMemoryType() {
        memoryDevice.setMemoryType(MemoryType.ROM)
        assertEquals(MemoryType.ROM, memoryDevice.getMemoryType())
        memoryDevice.setMemoryType(MemoryType.RAM)
        assertEquals(MemoryType.RAM, memoryDevice.getMemoryType())
    }
}
