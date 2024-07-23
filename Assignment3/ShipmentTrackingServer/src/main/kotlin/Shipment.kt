class Shipment(
    var status: String,
    var id: String,
    var notes: MutableList<String> = mutableListOf(),
    var updateHistory: MutableList<ShippingUpdate> = mutableListOf(),
    var expectedDeliveryDateTimestamp: Long? = null,
    var currentLocation: String = "Unknown",
    var observers: MutableList<UpdateObserver> = mutableListOf()
) : UpdateSubject {

    fun addNote(note: String) {
        notes.add(note)
    }

    fun addUpdate(updateStrategy: AddUpdateStrategy, updateValues: List<String>) {
        val newShippingUpdate = updateStrategy.addUpdate(this, updateValues)
        updateHistory.add(newShippingUpdate)
        notify(this)
    }

    override fun subscribe(observer: UpdateObserver) {
        observers.add(observer)
    }

    override fun unsubscribe(observer: UpdateObserver) {
        observers.remove(observer)
    }

    override fun notify(shipment: Shipment) {
        for (observer in observers) {
            observer.notify(shipment)
        }
    }
}