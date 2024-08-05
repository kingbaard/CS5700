import org.junit.jupiter.api.Assertions.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class TrackingServerTest {

    private lateinit var shipment: Shipment

    @BeforeTest
    fun setup() {
        shipment = Shipment("123", Pair(0L, 0L), "Warning")
    }

    @Test
    fun testFindShipment() {
        TrackingServer.addShipment(shipment)
        val foundShipment = TrackingServer.findShipment("123")
        assertNotNull(foundShipment)
        assertEquals("123", foundShipment?.id)
    }

    @Test
    fun testAddShipment() {
        TrackingServer.addShipment(shipment)
        val foundShipment = TrackingServer.findShipment("123")
        assertNotNull(foundShipment)
        assertEquals("123", foundShipment?.id)
    }

    @Test
    fun testHandleRequest() {
        TrackingServer.addShipment(shipment)
        TrackingServer.handleRequest("delivered,123,type,1628163845")
        val foundShipment = TrackingServer.findShipment("123")
        assertNotNull(foundShipment)
        assertEquals("123", foundShipment?.id)
        assertEquals("delivered", foundShipment?.status)
    }
}
