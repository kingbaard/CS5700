import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class TrackerViewHelper(
    private val _shipmentId: String
    ) : UpdateObserver {
    private val _shipmentTotes: List<String> = listOf()
    private var shipmentUpdateHistory = mutableStateListOf<ShippingUpdate>()
    private var shipmentNotes = mutableStateListOf<String>()
    private var _expectedShipmentDeliveryDate by mutableStateOf("Unknown")
    private var _shipmentStatus by mutableStateOf("Unknown")
    private var _location by mutableStateOf("Unknown")

    init {
        val shipment: Shipment? = TrackingServer.findShipment(_shipmentId)
        if (shipment != null) {
            println("DEBUG: subscribing to shipment $shipmentId")
            notify(shipment)
        }
    }

    val shipmentId: String
        get() = _shipmentId

    val expectedShipmentDeliveryDate: String
        get() = _expectedShipmentDeliveryDate

    val shipmentStatus: String
        get() = _shipmentStatus

    val location: String
        get() = _location

    @Composable
    fun render() {
        MaterialTheme {
            Column {
                Row {
                    Text(text = "Tracking Shipment #${shipmentId}")
                }
                Row {
                    Text(text = "Status: $shipmentStatus")
                }
                Row {
                    val etaLong : Long? = expectedShipmentDeliveryDate.toLongOrNull()
                    var displayString = expectedShipmentDeliveryDate
                    if (etaLong != null) {
                        displayString = convertLongToPrettyFormat(etaLong)
                    }
                    Text(text = "ETA: ${displayString}")
                }
                Row {
                    Text(text = "Location: $location")
                }
                Row {
                    Column {
                        Text(text = "Update History:")
                        shipmentUpdateHistory.forEach { update ->
                            val prettyTime = convertLongToPrettyFormat(update.timestamp)
                            Text(text = "  •Shipment went from ${update.previousStatus} to ${update.newStatus} on $prettyTime")
                        }
                    }

                }
                Row {
                    Column {
                        Text(text = "Notes:")
                        shipmentNotes.forEach { update ->
                            Text(text = "  •$update")
                        }
                    }
                }
                if (shipmentStatus == "problem") {
                    Row (
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(8.dp)) {
                        Text(
                            text = "Warning: ${TrackingServer.findShipment(_shipmentId)?.validRangeWarning}. Please contact support.",
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }

    fun convertLongToPrettyFormat(unixTime: Long) : String {
        val instant = Instant.ofEpochSecond(unixTime)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return dateTime.format(formatter)
    }

    fun removeTracking() {
        val shipment :Shipment? = TrackingServer.findShipment(_shipmentId)
        if (shipment != null) {
            shipment.unsubscribe(this)
        }
    }

    override fun notify(shipment: Shipment) {
        shipmentUpdateHistory.clear()
        shipmentUpdateHistory.addAll(shipment.updateHistory)
        _shipmentStatus = shipment.status
        _expectedShipmentDeliveryDate = shipment.expectedDeliveryDateTimestamp.toString()
        _location = shipment.currentLocation
    }
}
