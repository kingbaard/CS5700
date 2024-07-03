

class LineTest {

    //Generate test lines
    val unitVectorLine: Line = Line(Point(0.toDouble(),0.toDouble()), Point(1.toDouble(), 1.toDouble()))

    //Test move is accurate
//    @Test
    fun moveTest() {
        unitVectorLine.move(5.toDouble(), 5.toDouble())
    }

    //Test slope is accurate

    //Test length is accurate


}