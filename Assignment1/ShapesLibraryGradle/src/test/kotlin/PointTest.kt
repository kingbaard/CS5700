import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class PointTest {

    @Test
    fun testMove() {
        val point = Point(1.0, 2.0)
        point.move(3.0, 4.0)
        assertEquals(4.0, point.x)
        assertEquals(6.0, point.y)
    }

    @Test
    fun testClone() {
        val point = Point(5.0, 6.0)
        val clonedPoint = point.clone()
        assertEquals(point.x, clonedPoint.x)
        assertEquals(point.y, clonedPoint.y)
    }
}