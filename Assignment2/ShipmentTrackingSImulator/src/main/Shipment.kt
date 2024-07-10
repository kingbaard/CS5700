class Shipment {
    var status: String
    var id: String
    var notes: List<String> //private set
    var updateHistory: List<ShippingUpdate>
    var expectedDeliveryDateTimestamp: Long
    var currentLocation: String

    fun addNote(note: String) {

    }

    fun addUpdate(update: Update) {

    }
}