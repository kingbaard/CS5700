import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


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
        val shipment: Shipment? = TrackingSimulator.findShipment(_shipmentId)
        if (shipment != null) {
            notify(shipment)
        }
    }

    val shipmentId: String
        get() = _shipmentId

    val shipmentTotes: List<String>
        get() = _shipmentTotes

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
                    Text(text = "ETA: $expectedShipmentDeliveryDate")
                }
                Row {
                    Text(text = "Location: $location")
                }
                Row {
                    Column {
                        Text(text = "Update History:")
                        shipmentUpdateHistory.forEach { update ->
                            Text(text = "  •Shipment went from ${update.previousStatus} to ${update.newStatus} on ${update.timestamp}")
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
            }
        }
    }

    fun removeTracking() {
        val shipment :Shipment? = TrackingSimulator.findShipment(_shipmentId)
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
