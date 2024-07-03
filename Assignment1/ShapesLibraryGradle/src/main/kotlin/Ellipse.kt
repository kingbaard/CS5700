import kotlin.math.sqrt
import kotlin.math.PI
import kotlin.math.pow

open class Ellipse(startingPoint: Point,
                   val radiusPoint1: Point,
                   val radiusPoint2: Point) : Shape(listOf(startingPoint, radiusPoint1, radiusPoint2)){
    val radius1: Line = Line(startingPoint, radiusPoint1)
    val radius2: Line = Line(startingPoint, radiusPoint2)

    override fun area(): Double {
        return PI * radius1.length() * radius2.length()
    }

    override fun validate(): String {
        return "" //No invalid ellipse
    }

}