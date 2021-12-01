import nl.sbmf21.aoc20.Aoc
import org.junit.Test
import kotlin.test.assertEquals

class AocTest {

    private var aoc = Aoc()

    @Test
    fun names() {
        aoc.findDays().forEach { day ->
            assertEquals("Day${day.number}", day.javaClass.simpleName)
        }
    }
}
