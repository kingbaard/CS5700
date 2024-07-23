import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test


class AddUpdateLocationTest {

    private lateinit var shipment: Shipment

    @BeforeEach
    fun setUp() {
        shipment = Shipment(
            status = "Pending",
            id = "123",
            updateHistory = mutableListOf(),
            expectedDeliveryDateTimestamp = null,
            currentLocation = "Unknown"
        )
    }

    @Test
    fun `test addUpdate changes status, updates location and returns ShippingUpdate`() {
        // Arrange
        val updateStringSplit = listOf("Shipped", "123", System.currentTimeMillis().toString(), "New York")
        val strategy = AddUpdateLocation()

        // Act
        val shippingUpdate = strategy.addUpdate(shipment, updateStringSplit)

        // Assert
        assertEquals("Pending", shippingUpdate.previousStatus)
        assertEquals("Shipped", shippingUpdate.newStatus)
        assertEquals(updateStringSplit[2].toLong(), shippingUpdate.timestamp)
        assertEquals("Shipped", shipment.status)
        assertEquals("New York", shipment.currentLocation)
    }
}
