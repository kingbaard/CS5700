class Shipment(
    var status: String,
    var id: String,
    var notes: List<String> = listOf<String>(),
    var updateHistory: List<ShippingUpdate> = listOf<ShippingUpdate>(),
    var expectedDeliveryDateTimestamp: Long? = null,
    var currentLocation: String = "Unknown",
) {


    fun addNote(note: String) {

    }

    fun addUpdate(update: ShippingUpdate) {

    }
}