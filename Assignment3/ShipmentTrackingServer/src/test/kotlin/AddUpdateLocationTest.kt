import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AddUpdateLocationTest {

    @Test
    fun testAddUpdate() {
        val shipment = Shipment("123", Pair(0L, 0L), "Warning")
        val strategy = AddUpdateLocation()
        strategy.addUpdate(shipment, listOf("delivered", "2023-01-01", "", "", "New Location"))
        assertEquals("New Location", shipment.currentLocation)
    }
}
