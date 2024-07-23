import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AddUpdateTimeTest {

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
    fun `test addUpdate changes status, updates expectedDeliveryDateTimestamp and returns ShippingUpdate`() {
        // Arrange
        val expectedTimestamp = System.currentTimeMillis() + 1000000
        val updateStringSplit = listOf("Shipped", "123", System.currentTimeMillis().toString(), expectedTimestamp.toString())
        val strategy = AddUpdateTime()

        // Act
        val shippingUpdate = strategy.addUpdate(shipment, updateStringSplit)

        // Assert
        assertEquals("Pending", shippingUpdate.previousStatus)
        assertEquals("Shipped", shippingUpdate.newStatus)
        assertEquals(updateStringSplit[2].toLong(), shippingUpdate.timestamp)
        assertEquals("Shipped", shipment.status)
        assertEquals(expectedTimestamp, shipment.expectedDeliveryDateTimestamp)
    }
}
