abstract class Shape(val startingPoint: Point) {
    var points: List<Point> = emptyList()

    fun move(dx: Double, dy: Double) {
        for (point in points) {
            point.move(dx, dy)
        }
    }

    fun getAttributes() = points
    abstract fun area(): Double
    abstract fun validate(): String
}