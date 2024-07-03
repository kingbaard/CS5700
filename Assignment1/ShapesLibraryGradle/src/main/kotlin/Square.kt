import kotlin.math.absoluteValue

class Square(startingPoint: Point, oppositePoint: Point) : Rectangle(startingPoint, oppositePoint) {
    override fun validate(): String {
        if ((startingPoint.x - oppositePoint.x).absoluteValue != (startingPoint.y - oppositePoint.y).absoluteValue) {
            throw Exception("Width and height must be same value when making a square")
        }
        return super.validate()
    }
}