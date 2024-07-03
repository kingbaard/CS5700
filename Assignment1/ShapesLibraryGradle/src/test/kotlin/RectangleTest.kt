import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class RectangleTest {
    private lateinit var startingPoint: Point
    private lateinit var oppositePoint: Point
    private lateinit var rectangle: Rectangle

    @BeforeEach
    fun setUp() {
        startingPoint = Point(1.0, 2.0)
        oppositePoint = Point(4.0, 5.0)
        rectangle = Rectangle(startingPoint, oppositePoint)
    }

    @Test
    fun testInit() {
        assertEquals(listOf(startingPoint, oppositePoint), rectangle.points)
    }

    @Test
    fun testArea() {
        val expectedArea = 9.0  // (4 - 1) * (5 - 2) = 3 * 3 = 9
        assertEquals(expectedArea, rectangle.area())
    }

    @Test
    fun testValidate() {
        assertDoesNotThrow { rectangle.validate() }
    }

    @Test
    fun testValidateWidthZero() {
        val invalidRectangle = Rectangle(Point(1.0, 2.0), Point(1.0, 5.0))
        val exception = assertFailsWith<Exception> { invalidRectangle.validate() }
        assertEquals("Width cannot equal 0 when making a rectangle", exception.message)
    }

    @Test
    fun testValidateHeightZero() {
        val invalidRectangle = Rectangle(Point(1.0, 2.0), Point(4.0, 2.0))
        val exception = assertFailsWith<Exception> { invalidRectangle.validate() }
        assertEquals("Height cannot equal 0 when making a rectangle", exception.message)
    }
}