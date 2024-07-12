import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import jdk.javadoc.internal.doclets.formats.html.markup.Text

class TrackerViewHelper (
    var shipmentId : String, //by mutableStateOf(Shipment.id)
    val shipmentTotes: List<String>, //State<List<String>>
    val shipmentUpdateHistory: List<String>, //State<List<String>>
    var expectedShipmentDeliveryDate : String, //by mutableStateOf()
    var shipmentStatus : String //by mutableStateOf(Shipment.status)
) {

    var shipmentString: String = ""

    @Composable
    fun Render() {
        Column(){
            Text(text = shipmentString)
        }
    }

    fun updateShippingString() {

    }

    //TODO: add private setter to all attributes
    fun trackShipment(id: String) {

    }

    fun stopTracking() {
        
    }
}