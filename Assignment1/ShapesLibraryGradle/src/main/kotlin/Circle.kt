class Circle(val center : Point, val radiusPoint:Point) : Ellipse(
    center,
    radiusPoint1 = radiusPoint,
    radiusPoint2 = radiusPoint
    ) {
    override fun move(dx: Double, dy: Double) {
        center.move(dx, dy)
        radiusPoint.move(dx, dy)
    }
}