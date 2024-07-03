class Point(
    var x: Double,
    var y: Double,
){
    fun move(dx: Double, dy: Double) {
        x += dx
        y += dy
    }

    fun clone(): Point {
        return Point(x, y)
    }
}