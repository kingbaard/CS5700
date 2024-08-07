import org.example.RAM
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RAMTest {

    @Test
    fun testInitialization() {
        val ram = RAM(4096) // 4KB of RAM
        assertEquals(4096, ram.size)
        assertEquals(0, ram.read(0))
    }

    @Test
    fun testWriteAndRead() {
        val ram = RAM(4096)
        ram.write(0, 0xAA)
        assertEquals(0xAA, ram.read(0))
    }

    @Test
    fun testOutOfBounds() {
        val ram = RAM(4096)
        assertThrows(IndexOutOfBoundsException::class.java) {
            ram.write(4096, 0xFF)
        }
        assertThrows(IndexOutOfBoundsException::class.java) {
            ram.read(4096)
        }
    }
}
