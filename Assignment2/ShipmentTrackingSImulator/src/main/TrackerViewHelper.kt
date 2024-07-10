class TrackerViewHelper {
    val shipmentId: State<String>
    val shipmentTotes: State<List<String>>
    val shipmentUpdateHistory: State<List<String>>
    val expectedShipmentDeliveryDate: State<List<String>>
    val shipmentStatus: State<String>

    //TODO: add private setter to all attributes
    fun trackShipment(id: String) {

    }

    fun stopTracking() {
        
    }
}