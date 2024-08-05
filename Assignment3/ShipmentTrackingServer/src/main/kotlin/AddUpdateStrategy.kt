interface AddUpdateStrategy {
    //TODO("Look into whether it is okay to have an interface with such a concrete fun")
    fun addUpdate(shipment: Shipment, updateStringSplit: List<String>) : ShippingUpdate {
        val oldStatus = shipment.status
        shipment.status = updateStringSplit[0]
        // If delivery time is out of expected range
        if (shipment.expectedDeliveryDateTimestamp != null) {
            if (shipment.expectedDeliveryDateTimestamp!! < shipment.validRange.first || shipment.expectedDeliveryDateTimestamp!! > shipment.validRange.second) {
                shipment.status = "problem"
                return ShippingUpdate(oldStatus, shipment.status, updateStringSplit[3].toLong())
            }
        }
        return ShippingUpdate(oldStatus, shipment.status, updateStringSplit[3].toLong())
    }
}