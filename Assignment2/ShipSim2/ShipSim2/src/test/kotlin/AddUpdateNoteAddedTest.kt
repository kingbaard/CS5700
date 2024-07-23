import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AddUpdateNoteAddedTest {

    private lateinit var shipment: Shipment

    @BeforeEach
    fun setUp() {
        shipment = Shipment(
            status = "Pending",
            id = "123",
            updateHistory = mutableListOf(),
            expectedDeliveryDateTimestamp = null,
            currentLocation = "Unknown",
            notes = mutableListOf()
        )
    }

    @Test
    fun `test addUpdate changes status, adds note and returns ShippingUpdate`() {
        // Arrange
        val updateStringSplit = listOf("Shipped", "123", System.currentTimeMillis().toString(), "This is a test note.")
        val strategy = AddUpdateNoteAdded()

        // Act
        val shippingUpdate = strategy.addUpdate(shipment, updateStringSplit)

        // Assert
        assertEquals("Pending", shippingUpdate.previousStatus)
        assertEquals("Shipped", shippingUpdate.newStatus)
        assertEquals(updateStringSplit[2].toLong(), shippingUpdate.timestamp)
        assertEquals("Shipped", shipment.status)
        assertTrue(shipment.notes.contains("This is a test note."))
    }
}
