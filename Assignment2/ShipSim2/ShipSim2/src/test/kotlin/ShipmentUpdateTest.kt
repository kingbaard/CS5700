import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class ShippingUpdateTest {

    @Test
    fun initTest() {
        val previousStatus = "Pending"
        val newStatus = "Shipped"
        val timestamp = System.currentTimeMillis()

        val shippingUpdate = ShippingUpdate(previousStatus, newStatus, timestamp)

        assertEquals(previousStatus, shippingUpdate.previousStatus)
        assertEquals(newStatus, shippingUpdate.newStatus)
        assertEquals(timestamp, shippingUpdate.timestamp)
    }

    @Test
    fun statusChangeTest() {
        val shippingUpdate = ShippingUpdate("Pending", "Shipped", System.currentTimeMillis())

        // Change status
        val newStatus = "Delivered"
        shippingUpdate.newStatus = newStatus

        assertEquals(newStatus, shippingUpdate.newStatus)
    }

    @Test
    fun shippingUpdateTest() {
        val shippingUpdate = ShippingUpdate("Pending", "Shipped", System.currentTimeMillis())

        // Update timestamp
        val newTimestamp = System.currentTimeMillis() + 1000
        shippingUpdate.timestamp = newTimestamp

        assertEquals(newTimestamp, shippingUpdate.timestamp)
    }
}