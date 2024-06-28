abstract class Shape() {
    lateinit var points: List<Point>

    fun move(dx: Double, dy: Double) {
        for (point in points) {
            point.move(dx, dy)
        }
    }

    fun getAttributes() = points
    abstract fun area(): Double
    abstract fun validate(): Boolean
}