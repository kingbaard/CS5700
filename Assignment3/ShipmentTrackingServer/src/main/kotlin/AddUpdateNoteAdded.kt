class AddUpdateNoteAdded : AddUpdateStrategy {
    override fun addUpdate(shipment: Shipment, updateStringSplit: List<String>): ShippingUpdate {
        shipment.addNote(updateStringSplit[4])
        return super.addUpdate(shipment, updateStringSplit)
    }
}