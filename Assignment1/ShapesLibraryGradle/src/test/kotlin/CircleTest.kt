import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertEquals
import kotlin.test.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import kotlin.math.PI

class CircleTest {

    private lateinit var center: Point
    private lateinit var radiusPoint: Point
    private lateinit var circle: Circle

    @BeforeEach
    fun setUp() {
        center = Point(0.0, 0.0)
        radiusPoint = Point(3.0, 0.0)
        circle = Circle(center, radiusPoint)
    }

    @Test
    fun testArea() {
        val radius = 3.0
        val expectedArea = PI * radius * radius
        assertEquals(expectedArea, circle.area())
    }

    @Test
    fun testValidate() {
        assertDoesNotThrow { circle.validate()}
    }

    @Test
    fun testMove() {
        circle.move(1.0, 1.0)
        assertEquals(1.0, circle.center.y)  // Moved center
        assertEquals(4.0, circle.radiusPoint2.x)  // Moved radius point
    }
}