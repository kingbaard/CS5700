import kotlin.math.absoluteValue

open class Rectangle(startingPoint: Point, val oppositePoint: Point) : Shape(startingPoint) {
    fun init() {
        points = listOf(startingPoint, oppositePoint)
    }

    override fun area() = (startingPoint.x - oppositePoint.x).absoluteValue * (startingPoint.y - oppositePoint.y).absoluteValue

    override fun validate(): Boolean {
        // Check if width is 0
        if ((startingPoint.x - oppositePoint.x).absoluteValue == 0.toDouble()) {
            throw Exception("Width cannot equal 0 when making a rectangle")
        }
        if ((startingPoint.y - oppositePoint.y).absoluteValue == 0.toDouble()) {
            throw Exception("Height cannot equal 0 when making a rectangle")
        }
        return true
    }
}