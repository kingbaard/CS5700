import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import java.io.File

object TrackingSimulator {
    val shipments =  mutableStateListOf<Shipment>()
    val validShipmentIds : Set<String> = getValidIds()
    val file = File("test.txt")
    var time : Int? = getStartingTime()

    fun findShipment(id: String): Shipment? {
        if (validShipmentIds.contains(id)) {

        }
        else {
            return null
        }
    }

    fun addShipment(shipment: Shipment) {
        shipments.add(shipment)
    }

    fun runSimulation() {
        // If there are any updates in this second

    }

    fun readSecond() {}

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