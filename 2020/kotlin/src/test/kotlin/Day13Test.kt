import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.days.Day13
import org.junit.Test
import kotlin.test.assertEquals

class Day13Test {

    private var day = Day13(Aoc())

    @Test
    fun testPart1Output() = assertEquals(156, day.part1())

    @Test
    fun testPart2Output() = assertEquals(404517869995362, day.part2())
}
