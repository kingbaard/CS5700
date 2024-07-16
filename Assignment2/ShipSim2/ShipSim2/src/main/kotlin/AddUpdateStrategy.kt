interface AddUpdateStrategy {

    fun addUpdate(shipment: Shipment, updateStringSplit: List<String>) : ShippingUpdate {
        val oldStatus = shipment.status
        shipment.expectedDeliveryDateTimestamp = updateStringSplit[3].toLong()
        shipment.status = updateStringSplit[0]
        return ShippingUpdate(oldStatus, shipment.status, updateStringSplit[2].toLong())
    }
}