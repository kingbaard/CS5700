import org.junit.jupiter.api.Assertions.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class AddUpdateBasicTest {

    private lateinit var shipment: Shipment

    @BeforeTest
    fun setup() {
        shipment = Shipment("123", Pair(0L, 0L), "Warning")
    }

    @Test
    fun testAddUpdateBasic() {
        val strategy = AddUpdateBasic()
        val update = strategy.addUpdate(shipment, listOf("delivered", "123", "type", "1628163845"))
        assertEquals("delivered", shipment.status)
        assertNotNull(update)
    }

    @Test
    fun testAddUpdateLocation() {
        val strategy = AddUpdateLocation()
        strategy.addUpdate(shipment, listOf("delivered", "123", "type", "1628163845", "New Location"))
        assertEquals("New Location", shipment.currentLocation)
    }

    @Test
    fun testAddUpdateNoteAdded() {
        val strategy = AddUpdateNoteAdded()
        strategy.addUpdate(shipment, listOf("delivered", "123", "type", "1628163845", "New Note"))
        assertTrue(shipment.notes.contains("New Note"))
    }
}
