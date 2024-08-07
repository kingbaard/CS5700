import org.example.CPU
import org.example.InstructionJump
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CPUTest {

    @Test
    fun testInitialization() {
        val cpu = CPU()
        assertEquals(0, cpu.programCounter)
        assertEquals(0, cpu.timer)
        // Add more assertions for other registers and initial state
    }

    @Test
    fun testExecuteInstruction() {
        val cpu = CPU()
        val instruction = InstructionJump(0x004)
        cpu.executeInstruction(instruction)
        assertEquals(0x004, cpu.programCounter)
    }
}