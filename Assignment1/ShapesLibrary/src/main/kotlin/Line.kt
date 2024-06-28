import Line
import kotlin.math.sqrt
import kotlin.math.pow

class Line(
    val startingPoint: Point,
    val endingPoint: Point
) {
    fun getPoints(): Array<Point> {
        return arrayOf(startingPoint, endingPoint)
    }

    fun slope(): Double {
        return (endingPoint.y - startingPoint.y) / (endingPoint.x - startingPoint.x)
    }

    fun length(): Double {
        return sqrt((endingPoint.y - startingPoint.y).pow(2) + (endingPoint.x - startingPoint.x).pow(2))
    }

    fun move(dx: Double, dy: Double) {
        startingPoint.x += dx
        startingPoint.y += dy
        endingPoint.x += dx
        endingPoint.y += dy
    }
}