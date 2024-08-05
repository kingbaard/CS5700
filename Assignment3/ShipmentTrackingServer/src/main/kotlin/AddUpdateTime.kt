class AddUpdateTime : AddUpdateStrategy {
    override fun addUpdate(shipment: Shipment, updateStringSplit: List<String>): ShippingUpdate {
        shipment.expectedDeliveryDateTimestamp = updateStringSplit[4].toLong()
        return super.addUpdate(shipment, updateStringSplit)
    }
}