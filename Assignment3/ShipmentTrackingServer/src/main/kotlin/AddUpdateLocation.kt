class AddUpdateLocation : AddUpdateStrategy {
    override fun addUpdate(shipment: Shipment, updateStringSplit: List<String>): ShippingUpdate {
        shipment.currentLocation = updateStringSplit[4]
        return super.addUpdate(shipment, updateStringSplit)
    }
}