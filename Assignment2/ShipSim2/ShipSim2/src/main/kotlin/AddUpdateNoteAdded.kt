class AddUpdateNoteAdded : AddUpdateStrategy {
    override fun addUpdate(shipment: Shipment, updateStringSplit: List<String>): ShippingUpdate {
        shipment.addNote(updateStringSplit[3])
        return super.addUpdate(shipment, updateStringSplit)
    }
}