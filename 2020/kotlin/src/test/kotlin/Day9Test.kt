import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.days.Day9
import org.junit.Test
import kotlin.test.assertEquals

class Day9Test {

    private var day = Day9(Aoc())

    @Test
    fun testPart1Output() = assertEquals(25918798, day.part1())

    @Test
    fun testPart2Output() = assertEquals(3340942, day.part2())
}
