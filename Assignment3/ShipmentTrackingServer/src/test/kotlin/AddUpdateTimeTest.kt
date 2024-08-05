import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddUpdateTimeTest {

    private lateinit var shipment: Shipment

    @BeforeEach
    fun setup() {
        shipment = Shipment("123", Pair(0L, 0L), "Warning")
    }

    @Test
    fun testAddUpdate() {
        val strategy = AddUpdateTime()
        val update = strategy.addUpdate(shipment, listOf("delivered", "123", "type", "1628163845", "New Note"))
        assertEquals(1628163845L, shipment.expectedDeliveryDateTimestamp)
        assertNotNull(update)
    }
}
