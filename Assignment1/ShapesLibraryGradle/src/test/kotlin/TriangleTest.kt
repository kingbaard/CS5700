import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TriangleTest {

    private lateinit var cornerPoint1: Point
    private lateinit var cornerPoint2: Point
    private lateinit var cornerPoint3: Point
    private lateinit var triangle: Triangle

    @BeforeEach
    fun setUp() {
        cornerPoint1 = Point(0.0, 0.0)
        cornerPoint2 = Point(4.0, 0.0)
        cornerPoint3 = Point(0.0, 3.0)
        triangle = Triangle(cornerPoint1, cornerPoint2, cornerPoint3)
    }

    @Test
    fun testArea() {
        val expectedArea = 6.0  // (4 * 3) / 2 = 6.0
        assertEquals(expectedArea, triangle.area(), 1e-9, "Area calculation is incorrect")
    }

    @Test
    fun testValidateValidTriangle() {
        assertDoesNotThrow { triangle.validate() }
    }

    @Test
    fun testValidateCollinearPoints() {
        val invalidTriangle = Triangle(Point(0.0, 0.0), Point(2.0, 2.0), Point(4.0, 4.0))
        val exception = assertFailsWith<Exception> { invalidTriangle.validate() }
        assertEquals("The points are collinear and cannot form a triangle.", exception.message)
    }

    @Test
    fun testMove() {
        triangle.move(1.0, 1.0)
        val movedPoints = triangle.getAttributes()
        assertEquals(1.0, movedPoints[0].x)
        assertEquals(1.0, movedPoints[0].y)
        assertEquals(5.0, movedPoints[1].x)
        assertEquals(1.0, movedPoints[1].y)
    }
}