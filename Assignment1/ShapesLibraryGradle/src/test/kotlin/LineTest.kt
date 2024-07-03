import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.math.sqrt
import kotlin.test.Test

class LineTest {

    //Generate test lines
    val unitVectorLine: Line = Line(Point(0.toDouble(),0.toDouble()), Point(1.toDouble(), 1.toDouble()))

    //Test move is accurate
    @Test
    fun moveTest() {
        unitVectorLine.move(5.toDouble(), 5.toDouble())
        assertEquals(5.toDouble(), unitVectorLine.startingPoint.x)
    }

    //Test slope is accurate
    @Test
    fun slopeTest() {
        assertEquals(1.toDouble(), unitVectorLine.slope())
    }

    //Test length is accurate
    @Test
    fun lengthTest() {
        assertEquals(sqrt(2.toDouble()), unitVectorLine.length())
    }


}