import org.junit.jupiter.api.Assertions.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class TrackerViewHelperTest {

    private lateinit var helper: TrackerViewHelper

    @BeforeTest
    fun setup() {
        helper = TrackerViewHelper("123")
    }

    @Test
    fun testShipmentId() {
        assertEquals("123", helper.shipmentId)
    }

    @Test
    fun testExpectedShipmentDeliveryDate() {
        assertEquals("Unknown", helper.expectedShipmentDeliveryDate)
    }

    @Test
    fun testShipmentStatus() {
        assertEquals("Unknown", helper.shipmentStatus)
    }

    @Test
    fun testLocation() {
        assertEquals("Unknown", helper.location)
    }
}
