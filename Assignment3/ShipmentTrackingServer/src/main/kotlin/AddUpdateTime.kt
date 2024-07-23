class AddUpdateTime : AddUpdateStrategy {
    override fun addUpdate(shipment: Shipment, updateStringSplit: List<String>): ShippingUpdate {
        shipment.expectedDeliveryDateTimestamp = updateStringSplit[3].toLong()
        return super.addUpdate(shipment, updateStringSplit)
    }
}