import io.frutsel_.aoc.Aoc
import org.junit.Test
import kotlin.test.assertEquals

class AocTest {

    private var aoc = Aoc()

    @Test
    fun names() {
        aoc.findDays().forEach { day ->
            assertEquals("Day${day.number()}", day.javaClass.simpleName)
        }
    }
}
