//TODO("Look into whether it is okay to have an interface")
class AddUpdateBasic : AddUpdateStrategy {
    override fun addUpdate(shipment: Shipment, updateStringSplit: List<String>): ShippingUpdate {
        return super.addUpdate(shipment, updateStringSplit)
    }
}