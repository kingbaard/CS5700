import androidx.compose.runtime.mutableStateMapOf
import java.io.File

object TrackingSimulator {
    val shipments =  mutableStateMapOf<String, Shipment>()
    val file = File("test.txt")
    var time : Int? = getStartingTime()
    val simFileMap: MutableMap<String, MutableList<String>> = mutableMapOf<String, MutableList<String>>()

    fun init(){
        file.forEachLine { line ->
            val columns = line.split(",")
            val list = simFileMap.getOrPut(columns[2]) { mutableListOf() }
            list.add(line)
        }
    }

    fun findShipment(queryId: String): Shipment? {
        return if (shipments.keys.contains(queryId)) {
            shipments[queryId]
        } else {
            null
        }
    }

    fun addShipment(shipment: Shipment) {
        shipments[shipment.id] = shipment
    }

    fun runSimulation() {
        // If there are any updates in this second
        if (simFileMap.keys.contains(time.toString())) { processSecond(simFileMap[time.toString()])}
    }

    fun processSecond(updateStrings : MutableList<String>?) {
        updateStrings?.forEach{ updateString ->
            val updateStringSplit: List<String> = updateString.split(",")
            var shippingUpdate : ShippingUpdate? = null
            if (shipments.keys.contains(updateStringSplit[2])) {
                when (updateStringSplit[0].trim()) {
                    "created" -> {
                        addShipment(Shipment("created", updateStringSplit[0]))
                    }
                    "shipped" -> shipments[updateStringSplit[0]]
                    "location" -> println("location")
                    "delayed" -> println("delayed")
                    "noteadded" -> println("noteadded")
                    else -> println("WARNING: unhandled update type detected in simulation file.")
                }
            }

        }
    }

    fun shipmentFactory(id:String): Shipment {
        return Shipment("created", id)
    }

    fun getStartingTime() : Int? {
        file.useLines { lines ->
            val firstLine = lines.firstOrNull()
            if (firstLine != null) {
                return firstLine.split(',')[2].toInt()
            }
            return null
        }
    }

    fun getValidIds() : Set<String> {
        val uniqueIds = mutableSetOf<String>()
        file.forEachLine { line ->
            val columns = line.split(',')
            if (columns[3].toInt() > time!!) return@forEachLine
            uniqueIds.add(columns[2])
        }
        return uniqueIds
    }
}