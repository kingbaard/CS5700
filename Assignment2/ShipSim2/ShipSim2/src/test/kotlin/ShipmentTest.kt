import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ShipmentTest {
    private lateinit var shipment: Shipment

    @BeforeEach
    fun setUp() {
        shipment = Shipment(
            status = "pending",
            id = "123",
            notes = mutableListOf(),
            updateHistory = mutableListOf(),
            expectedDeliveryDateTimestamp = null,
            currentLocation = "Unknown",
            observers = mutableListOf()
        )
    }

    @Test
    fun testAddNote() {
        shipment.addNote("This is a test note.")
        assertEquals(1, shipment.notes.size)
        assertEquals("This is a test note.", shipment.notes[0])
    }

    @Test
    fun testSubscribe() {
        val observer = object : UpdateObserver {
            override fun notify(shipment: Shipment) {}
        }

        shipment.subscribe(observer)
        assertEquals(1, shipment.observers.size)
        assertTrue(shipment.observers.contains(observer))
    }

    @Test
    fun testUnsubscribe() {
        val observer = object : UpdateObserver {
            override fun notify(shipment: Shipment) {}
        }

        shipment.subscribe(observer)
        shipment.unsubscribe(observer)
        assertEquals(0, shipment.observers.size)
    }

    @Test
    fun testNotifyObservers() {
        var notified = false
        val observer = object : UpdateObserver {
            override fun notify(shipment: Shipment) {
                notified = true
            }
        }

        shipment.subscribe(observer)
        shipment.notify(shipment)
        assertTrue(notified)
    }
}
