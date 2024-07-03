import kotlin.math.sqrt

class Ellipse(startingPoint: Point, val radiusPoint1: Point, val radiusPoint2: Point) : Shape(startingPoint){
    val radius1: Line = Line(startingPoint, radiusPoint1)
    val radius2: Line = Line(startingPoint, radiusPoint2)

    override fun area(): Double {
        return sqrt(math.pow(radius1.length(), 2) + math.pow(radius2.length(), 2)) / 2
    }

    override fun validate(): String {
        TODO("Not yet implemented")
    }

}