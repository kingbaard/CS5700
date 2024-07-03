class Square(startingPoint: Point, oppositePoint: Point) : Rectangle(startingPoint, oppositePoint) {
    override fun validate(): Boolean
}