import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SquareTest {

    private lateinit var startingPoint: Point
    private lateinit var oppositePoint: Point
    private lateinit var square: Square

    @BeforeEach
    fun setUp() {
        startingPoint = Point(0.0, 0.0)
        oppositePoint = Point(3.0, 3.0)
        square = Square(startingPoint, oppositePoint)
    }

    @Test
    fun testArea() {
        val expectedArea = 9.0  // 3 * 3
        assertEquals(expectedArea, square.area(), 1e-9, "Area calculation is incorrect")
    }

    @Test
    fun testValidateValidSquare() {
        assertDoesNotThrow { square.validate() }
    }

    @Test
    fun testValidateInvalidSquare() {
        val invalidSquare = Square(Point(0.0, 0.0), Point(3.0, 4.0))
        val exception = assertFailsWith<Exception> { invalidSquare.validate() }
        assertEquals("Width and height must be same value when making a square", exception.message)
    }

    @Test
    fun testMove() {
        square.move(1.0, 0.0)
        assertEquals(1.0, square.startingPoint.x)
        square.move(5.0, -10.0)
        assertEquals(6.0, square.startingPoint.x)
        assertEquals(-10.0, square.startingPoint.y)
    }
}