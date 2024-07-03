abstract class Shape(var points: List<Point>) {

    fun move(dx: Double, dy: Double) {
        for (point in points) point.move(dx, dy)
    }

    fun getAttributes() = points
    abstract fun area(): Double
    abstract fun validate(): String
}