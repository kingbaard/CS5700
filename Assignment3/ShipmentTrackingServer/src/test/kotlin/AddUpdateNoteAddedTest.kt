import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AddUpdateNoteAddedTest {

    @Test
    fun testAddUpdate() {
        val shipment = Shipment("123", Pair(0L, 0L), "Warning")
        val strategy = AddUpdateNoteAdded()
        strategy.addUpdate(shipment, listOf("delivered", "2023-01-01", "", "", "New Note"))
        assertTrue(shipment.notes.contains("New Note"))
    }
}