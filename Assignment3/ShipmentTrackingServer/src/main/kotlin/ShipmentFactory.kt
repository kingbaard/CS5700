import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class ShipmentFactory() {

    fun createShipment(id: String, type: String) : Shipment {
        val threeDaysInSec = 86400 * 3 //86400 seconds in a day
        val currentTime : Long = Instant.now().epochSecond
        var invalidTimeMessage : String = ""

        // Set respective valid time range and warning messages
        val validRange: Pair<Long, Long> = when (type) {
            "bulk" -> {
                invalidTimeMessage = "This bulk shipment is expected to arrive early than 3 days before it was created."
                Pair(currentTime + threeDaysInSec, Long.MAX_VALUE)
            }
            "express" -> {
                invalidTimeMessage = "This express shipment is expected to arrive more than 3 days after it was created."
                Pair(currentTime, currentTime + threeDaysInSec)
            }
            "overnight" -> {
                invalidTimeMessage = "This overnight shipment is expected to arrive past its intended date"
                val myZoneId = ZoneId.systemDefault()
                val midnightTomorrow : Long = LocalDate.now(myZoneId).plusDays(2).atStartOfDay(myZoneId).toEpochSecond()
                Pair(currentTime, midnightTomorrow)
            }
            "standard" -> Pair(currentTime, Long.MAX_VALUE)
            else -> {
                println("WARN: unknown shipment type passed: $type")
                Pair(currentTime, Long.MAX_VALUE)
            }
        }
        return Shipment(id, validRange, invalidTimeMessage)
    }
}