import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AddUpdateBasicTest {

    private lateinit var shipment: Shipment

    @BeforeEach
    fun setUp() {
        shipment = Shipment(
            status = "Pending",
            id = "123",
            updateHistory = mutableListOf(),
            expectedDeliveryDateTimestamp = null
        )
    }

    @Test
    fun `test addUpdate changes status and returns ShippingUpdate`() {
        // Arrange
        val updateStringSplit = listOf("Shipped", "123", System.currentTimeMillis().toString())
        val strategy = AddUpdateBasic()

        // Act
        val shippingUpdate = strategy.addUpdate(shipment, updateStringSplit)

        // Assert
        assertEquals("Pending", shippingUpdate.previousStatus)
        assertEquals("Shipped", shippingUpdate.newStatus)
        assertEquals(updateStringSplit[2].toLong(), shippingUpdate.timestamp)
        assertEquals("Shipped", shipment.status)
    }
}
