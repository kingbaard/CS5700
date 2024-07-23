import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class TrackingSimulatorTest {

    @Test
    fun getSimDataTest() {
        val simData = TrackingSimulator.getSimData()
        assertNotNull(simData)
        assertTrue(simData.isNotEmpty())
        assertEquals("pending", TrackingSimulator.shipments["s10000"]?.status)
    }

    @Test
    fun findShipmentTest() {
        val shipment = Shipment("testStatus", "testId")
        TrackingSimulator.addShipment(shipment)
        val foundShipment = TrackingSimulator.findShipment("testId")
        assertNotNull(foundShipment)
        assertEquals(shipment.status, foundShipment?.status)
    }

    @Test
    fun addShipmentTest() {
        val shipment = Shipment("testStatus", "testId")
        TrackingSimulator.addShipment(shipment)
        assertTrue(TrackingSimulator.shipments.containsKey("testId"))
    }
}
