import kotlin.math.abs

class Triangle(val cornerPoint1:Point,
               val cornerPoint2:Point,
               val cornerPoint3:Point):Shape(listOf(cornerPoint1, cornerPoint2, cornerPoint3)) {

    override fun area(): Double {
        val x1 = cornerPoint1.x
        val y1 = cornerPoint1.y
        val x2 = cornerPoint2.x
        val y2 = cornerPoint2.y
        val x3 = cornerPoint3.x
        val y3 = cornerPoint3.y

        return 0.5 * abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))
    }

    override fun validate(): String {
        return if (area() == 0.0) {
            throw Exception("The points are collinear and cannot form a triangle.")
        } else {
            ""
        }
    }
}