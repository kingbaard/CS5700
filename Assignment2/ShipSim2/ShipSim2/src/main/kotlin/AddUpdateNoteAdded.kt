class AddUpdateNoteAdded : AddUpdateStrategy {
    override fun addUpdate(shipment: Shipment, updateStringSplit: List<String>): ShippingUpdate {
        shipment.notes.add(updateStringSplit[3])
        return super.addUpdate(shipment, updateStringSplit)
    }
}