import androidx.compose.runtime.mutableStateMapOf
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.io.File

object TrackingSimulator {
    val shipments =  mutableStateMapOf<String, Shipment>()
    val file = File("test.txt")
    val simFileMap: MutableMap<String, MutableList<String>> = getSimData()
    var time : Int = getStartingTime()

    fun getSimData() : MutableMap<String, MutableList<String>> {
        val simData : MutableMap<String, MutableList<String>> = mutableMapOf()
        file.forEachLine { line ->
            val columns = line.split(",")
            val list = simData.getOrPut(columns[2]) { mutableListOf() }
            list.add(line)
        }
        return simData
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

    suspend fun runSimulation() {
        coroutineScope {
            if (simFileMap.keys.contains(time.toString())) { processSecond(simFileMap[time.toString()])}
            delay(1_000)
            time ++
        }
    }

    fun processSecond(updateStrings : MutableList<String>?) {
        updateStrings?.forEach{ updateString ->
            val updateStringSplit: List<String> = updateString.split(",")
            var shippingUpdate : ShippingUpdate? = null
            if (shipments.keys.contains(updateStringSplit[2])) {
                val shipment: Shipment = shipments[updateStringSplit[2]] ?: return
                val updateMethod: AddUpdateStrategy = when (updateStringSplit[0].trim()) {
                    "location" -> AddUpdateLocation()
                    "noteadded" -> AddUpdateNoteAdded()
                    "delayed" -> AddUpdateTime()
                    else -> AddUpdateBasic()
                }
                updateMethod.addUpdate(shipment, updateStringSplit)
            }

        }
    }

    fun shipmentFactory(id:String): Shipment {
        return Shipment("created", id)
    }

    private fun getStartingTime() : Int {
        val allTimes = simFileMap.keys.mapNotNull { it.toIntOrNull() }
        return allTimes.min()
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