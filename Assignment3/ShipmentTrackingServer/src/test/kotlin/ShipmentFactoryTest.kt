import org.junit.jupiter.api.Assertions.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class ShipmentFactoryTest {

    private lateinit var factory: ShipmentFactory

    @BeforeTest
    fun setup() {
        factory = ShipmentFactory()
    }

    @Test
    fun testCreateShipment() {
        val shipment = factory.createShipment("123", "bulk")
        assertNotNull(shipment)
        assertEquals("123", shipment.id)
        assertEquals("This bulk shipment is expected to arrive early than 3 days before it was created.", shipment.validRangeWarning)
    }
}
