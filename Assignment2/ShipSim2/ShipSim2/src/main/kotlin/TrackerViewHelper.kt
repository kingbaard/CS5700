import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
) : UpdateObserver {

    @Composable
    fun Render() {
        Column(){
            Row(){
                //ID goes here

                //
            }
            Text(text = shipmentStatus)
        }
    }

    //TODO: add private setter to all attributes
    fun trackShipment(id: String) {

    }

    fun stopTracking() {
        
    }

    override fun notify(update: ShippingUpdate) {
        TODO("Not yet implemented")
    }
}