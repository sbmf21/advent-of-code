import io.frutsel_.aoc.Aoc
import org.junit.Test
import kotlin.test.assertEquals

internal class Expectation(val part1: Number, val part2: Number)

class AocTest {

    private val expectations = listOf(
        Expectation(1019904, 176647680)
    )

    @Test
    fun outputs() {
        Aoc().findDays().forEach { day ->
            val expectation = expectations[day.number() - 1]

            assertEquals(expectation.part1, day.part1())
            assertEquals(expectation.part2, day.part2())
        }
    }

    @Test
    fun names() {
        Aoc().findDays().forEach { day ->
            assertEquals("Day${day.number()}", day.javaClass.simpleName)
        }
    }
}