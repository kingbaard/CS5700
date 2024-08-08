import org.example.ROM
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@OptIn(ExperimentalUnsignedTypes::class)
class ROMTest {

    @Test
    fun testInitialization() {
        val data = UByteArray(4096) { 0x00.toUByte() }
        val rom = ROM(data)
        assertEquals(4098, rom.data.size)
        assertEquals(0x00.toUByte(), rom.read(0))
    }

    @Test
    fun testRead() {
        val data = UByteArray(4096) { it.toUByte() }
        val rom = ROM(data)
        assertEquals(0x00.toUByte(), rom.read(0))
        assertEquals(0xFF, rom.read(255).toInt() and 0xFF)
    }

    @Test
    fun testWriteShouldFail() {
        val data = UByteArray(4096) { 0x00.toUByte() }
        val rom = ROM(data)
        assertThrows(IllegalAccessException::class.java) {
            rom.write(0, 0xFF.toUByte())
        }
    }

    @Test
    fun testOutOfBounds() {
        val data = UByteArray(4096) { 0x00.toUByte() }
        val rom = ROM(data)
        assertThrows(IndexOutOfBoundsException::class.java) {
            rom.read(5000)
        }
    }
}
