import kotlin.math.PI

open class Ellipse(center: Point,
                   val radiusPoint1: Point,
                   val radiusPoint2: Point) : Shape(listOf(center, radiusPoint1, radiusPoint2)){
    val radius1: Line = Line(center, radiusPoint1)
    val radius2: Line = Line(center, radiusPoint2)

    override fun area(): Double {
        return PI * radius1.length() * radius2.length()
    }

    override fun validate(): String {
        return "" //No invalid ellipse
    }

}