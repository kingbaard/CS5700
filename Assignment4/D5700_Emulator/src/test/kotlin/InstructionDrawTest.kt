//import org.example.D5700Emulator
//import org.example.MemoryDevice
//import org.example.RAM
//import org.example.ROM
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.BeforeEach
//import java.io.ByteArrayInputStream
//import java.io.ByteArrayOutputStream
//import java.io.PrintStream
//
//@OptIn(ExperimentalUnsignedTypes::class)
//class InstructionDrawTest {
//
//    private lateinit var ram: RAM
//    private lateinit var rom: ROM
//
//    @BeforeEach
//    fun setUp() {
//        D5700Emulator.invoke()
//        Thread.sleep(3000)
//        val inputStream = ByteArrayInputStream("roms/draw_b.out".toByteArray())
//        System.setIn(inputStream)
//
//    }
//
//    @Test
//    fun testOutput() {
//        println(D5700Emulator.screen.frameBuffer[0])
//    }
//
//}
