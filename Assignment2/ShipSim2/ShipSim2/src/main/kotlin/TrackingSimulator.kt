import androidx.compose.runtime.mutableStateMapOf
import kotlinx.coroutines.*
import java.io.File
import java.math.BigInteger

object TrackingSimulator {
    val file = File("test.txt")
    val shipments = mutableStateMapOf<String, Shipment>()
    var time: BigInteger = 0.toBigInteger()
    val simFileMap: MutableMap<String, MutableList<List<String>>> = getSimData()

    fun getSimData(): MutableMap<String, MutableList<List<String>>> {
        val simData: MutableMap<String, MutableList<List<String>>> = mutableMapOf()
        file.forEachLine { line ->
            //populate simData
            val columns = line.split(",")
            val list = simData.getOrPut(columns[2]) { mutableListOf() }
            list.add(columns)

            // populate shipments
            if (!shipments.keys.contains(columns[1])) {
                val newShipment : Shipment = Shipment("pending", columns[1])
                addShipment(newShipment)
            }
        }
        // set time to the lowest sim value
        val allTimes : List<BigInteger> = simData.keys.map {it.toBigInteger()}
        val minTime = allTimes.min()
        println(minTime)
        time = allTimes.min()

        return simData
    }

    fun findShipment(queryId: String): Shipment? {
        return shipments[queryId]
    }

    fun addShipment(shipment: Shipment) {
        shipments[shipment.id] = shipment
    }

    suspend fun runSimulation() = coroutineScope {
        launch {
            while (true) {
                println("Processing second $time")
                if (simFileMap.containsKey(time.toString())) {
                    processSecond(simFileMap[time.toString()])
                }
                delay(1_000)
                time++
            }
        }
    }

    fun processSecond(updateStrings: MutableList<List<String>>?) {
        updateStrings?.forEach { updateString ->
            if (simFileMap.containsKey(updateString[2])) {
                val shipment: Shipment? = shipments[updateString[1]]
                val updateMethod: AddUpdateStrategy = when (updateString[0].trim()) {
                    "location" -> AddUpdateLocation()
                    "noteadded" -> AddUpdateNoteAdded()
                    "shipped", "delayed" -> AddUpdateTime()
                    else -> AddUpdateBasic()
                }
                shipment?.addUpdate(updateMethod, updateString)
            }
        }
    }
}
