import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith


class EllipseTest {

    @Test
    fun testArea() {
        val center = Point(0.0, 0.0)
        val radiusPoint1 = Point(5.0, 0.0) // Semi-major axis point
        val radiusPoint2 = Point(0.0, 3.0) // Semi-minor axis point

        val ellipse = Ellipse(center, radiusPoint1, radiusPoint2)
        val expectedArea = Math.PI * 5.0 * 3.0 // π * a * b

        assertEquals(expectedArea, ellipse.area(), 1e-9, "Area calculation is incorrect")
    }

    @Test
    fun testRadiusLengths() {
        val center = Point(0.0, 0.0)
        val radiusPoint1 = Point(5.0, 0.0) // Semi-major axis point
        val radiusPoint2 = Point(0.0, 3.0) // Semi-minor axis point

        val ellipse = Ellipse(center, radiusPoint1, radiusPoint2)

        assertEquals(5.0, ellipse.radius1.length(), 1e-9, "Radius1 length calculation is incorrect")
        assertEquals(3.0, ellipse.radius2.length(), 1e-9, "Radius2 length calculation is incorrect")
    }
}