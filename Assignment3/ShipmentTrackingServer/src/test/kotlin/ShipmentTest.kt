import org.junit.jupiter.api.Assertions.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class ShipmentTest {

    private lateinit var shipment: Shipment

    @BeforeTest
    fun setup() {
        shipment = Shipment("123", Pair(0L, 0L), "Warning")
    }

    @Test
    fun testAddNote() {
        shipment.addNote("Test Note")
        assertTrue(shipment.notes.contains("Test Note"))
    }

    @Test
    fun testAddUpdate() {
        val strategy = AddUpdateBasic()
        shipment.addUpdate(strategy, listOf("delivered", "123", "type", "1628163845"))
        assertEquals("delivered", shipment.status)
        assertEquals(1, shipment.updateHistory.size)
    }

    @Test
    fun testSubscribe() {
        val observer = TrackerViewHelper("123")
        shipment.subscribe(observer)
        assertTrue(shipment.observers.contains(observer))
    }

    @Test
    fun testUnsubscribe() {
        val observer = TrackerViewHelper("123")
        shipment.subscribe(observer)
        shipment.unsubscribe(observer)
        assertFalse(shipment.observers.contains(observer))
    }
}
