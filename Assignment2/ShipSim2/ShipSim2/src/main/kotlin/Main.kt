import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import App as App1

@Composable
@Preview
suspend fun App() {
    var text by remember { mutableStateOf("") }
    val trackerViewHelperList : List<TrackerViewHelper> =  mutableListOf<TrackerViewHelper>()
    TrackingSimulator.runSimulation()

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Enter Shipping Id") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        addShipmentOnClick(text)
                        text = ""
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("Add Shipment")
                }
            }
            LazyColumn {
                items(trackerViewHelperList, key = { it.shipmentId }) { viewHelper ->
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                            .padding(4.dp)
                    ) {
                        Text(text = viewHelper.shipmentStatus)
                    }
                }
            }
        }
    }
}

fun addShipmentOnClick(inputId: String) {
    if (inputId.isNotBlank()) {
        val shipment: Shipment? = TrackingSimulator.findShipment(inputId)
        if (shipment != null) {
            TrackingSimulator.addShipment(shipment)
        }
        else {
            println("User typed invalid tracking number.")
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App1()
    }
}
