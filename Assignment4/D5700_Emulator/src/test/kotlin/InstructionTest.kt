package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalUnsignedTypes::class)
class InstructionTest {

    private lateinit var byteOrganizer: BytesOrganizer
    private lateinit var programCounter: Registry
    private lateinit var instruction: TestInstruction

    @BeforeEach
    fun setUp() {
        val registryFactory = RegistryFactory()
        byteOrganizer = OrganizerOneByte()
        programCounter = registryFactory.createRegistry(RegistryType.ONE_BYTE)
        instruction = TestInstruction(byteOrganizer)
    }

    @Test
    fun testExecute() {
        assertEquals(programCounter.getValueAsInt(), 0)
        instruction.execute(UByteArray(4), programCounter)
        assertEquals(programCounter.getValueAsInt(), 2)
    }

    @Test
    fun testIncrementPC() {
        assertEquals(programCounter.getValueAsInt(), 0)
        instruction.incrementPC((programCounter))
        assertEquals(programCounter.getValueAsInt(), 2)
    }

    // A concrete implementation of the abstract Instruction class for testing purposes
    class TestInstruction(
        override val byteOrganizer: BytesOrganizer
    ) : Instruction() {
        override var shouldIncrement: Boolean = true

        override fun performOperation(parameters: List<Int>) {
            // No-op for testing
        }
    }
}
