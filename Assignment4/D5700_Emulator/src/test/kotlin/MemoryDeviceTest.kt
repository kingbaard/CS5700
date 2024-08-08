import org.example.MemoryDevice
import org.example.RAM
import org.example.ROM
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

@OptIn(ExperimentalUnsignedTypes::class)
class MemoryDeviceTest {

    private lateinit var ram: RAM
    private lateinit var rom: ROM

    @BeforeEach
    fun setUp() {
        val romData = ByteArray(4096) { it.toByte() }
        rom = ROM(UByteArray(3998){ it.toUByte()})
        ram = RAM(null)
    }

    @Test
    fun testReadFromRAM() {
        ram.write(0, 0xAA.toUByte())
        assertEquals(0xAA.toUByte(), ram.read(0))
    }

    @Test
    fun testReadFromROM() {
        assertEquals(0x00.toUByte(), rom.read(0))
        assertEquals(0xFF, rom.read(255).toInt() and 0xFF)
    }

    @Test
    fun testWriteToRAM() {
        ram.write(0, 0xAA.toUByte())
        assertEquals(0xAA.toUByte(), ram.read(0))
    }

}
