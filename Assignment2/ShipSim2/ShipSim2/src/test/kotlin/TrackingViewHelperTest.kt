import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.concurrent.CopyOnWriteArrayList

class TrackerViewHelperTest {

    private lateinit var shipment: Shipment
    private lateinit var trackerViewHelper: TrackerViewHelper
    private lateinit var trackingSimulator: TrackingSimulator

    @BeforeEach
    fun setUp() {
        // Initialize the shipment
        shipment = Shipment(
            status = "Pending",
            id = "123",
            updateHistory = mutableListOf(
                ShippingUpdate("Created", "Pending", System.currentTimeMillis())
            ),
            expectedDeliveryDateTimestamp = System.currentTimeMillis(),
            currentLocation = "Warehouse"
        )

        // Initialize the TrackingSimulator
        trackingSimulator = TrackingSimulator.apply {
            shipments.clear()
            shipments["123"] = shipment
        }

        // Initialize the TrackerViewHelper
        trackerViewHelper = TrackerViewHelper("123")
    }

    @Test
    fun updatesInitTest() {
        assertEquals("123", trackerViewHelper.shipmentId)
        assertEquals("Pending", trackerViewHelper.shipmentStatus)
        assertEquals(shipment.expectedDeliveryDateTimestamp.toString(), trackerViewHelper.expectedShipmentDeliveryDate)
        assertEquals("Warehouse", trackerViewHelper.location)
    }

    @Test
    fun `notifyTest`() {
        val newShipment = Shipment(
            status = "In Transit",
            id = "123",
            updateHistory = mutableListOf(
                ShippingUpdate("Created", "Pending", System.currentTimeMillis()),
                ShippingUpdate("Pending", "In Transit", System.currentTimeMillis())
            ),
            expectedDeliveryDateTimestamp = System.currentTimeMillis() + 1000000,
            currentLocation = "On the way"
        )

        trackerViewHelper.notify(newShipment)

        assertEquals("In Transit", trackerViewHelper.shipmentStatus)
        assertEquals(newShipment.expectedDeliveryDateTimestamp.toString(), trackerViewHelper.expectedShipmentDeliveryDate)
        assertEquals("On the way", trackerViewHelper.location)
    }

    @Test
    fun removeTrackingTest() {
        // Add the trackerViewHelper as an observer
        shipment.subscribe(trackerViewHelper)
        assertTrue(shipment.observers.contains(trackerViewHelper))

        // Remove the tracking
        trackerViewHelper.removeTracking()

        // Verify the trackerViewHelper is unsubscribed
        assertFalse(shipment.observers.contains(trackerViewHelper))
    }
}