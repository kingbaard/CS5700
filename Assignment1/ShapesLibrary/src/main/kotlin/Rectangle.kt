import kotlin.math.absoluteValue

class Rectangle(val cornerPoint1: Point, val cornerPoint2: Point) : Shape() {
    fun init() {
        points = listOf(cornerPoint1, cornerPoint2)
    }

    override fun area() = (cornerPoint1.x - cornerPoint2.x).absoluteValue * (cornerPoint1.y - cornerPoint2.y).absoluteValue

    override fun validate(): Boolean {
        // Check if width is 0
        if ((cornerPoint1.x - cornerPoint2.x).absoluteValue == 0.toDouble()) {
            throw Exception("Width cannot equal 0 when making a rectangle")
        }
        if ((cornerPoint1.y - cornerPoint2.y).absoluteValue == 0.toDouble()) {
            throw Exception("Height cannot equal 0 when making a rectangle")
        }
        return true
    }
}