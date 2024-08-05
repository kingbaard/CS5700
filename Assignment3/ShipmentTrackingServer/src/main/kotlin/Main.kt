import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("") }
    val trackerViewHelperList = remember { mutableStateListOf<TrackerViewHelper>() }

    LaunchedEffect(Unit) {
        TrackingServer.startServer()
    }

    fun addShipmentOnClick(inputId: String) {
        if (inputId.isNotBlank()) {
            val shipment: Shipment? = TrackingServer.findShipment(inputId)
            if (shipment != null) {
                val newTrackerViewHelper = TrackerViewHelper(_shipmentId = shipment.id)
                trackerViewHelperList.add(newTrackerViewHelper)
                shipment.subscribe(newTrackerViewHelper)
            } else {
                println("User typed invalid tracking number.")
            }
        }
    }

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
                    Column (
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.LightGray)
                            .fillMaxWidth()
                    ) {
                        viewHelper.render()
                        Button(
                            onClick = {
                                viewHelper.removeTracking()
                                trackerViewHelperList.remove(viewHelper)
                            },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
