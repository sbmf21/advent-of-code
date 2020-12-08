import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.days.Day8
import org.junit.Test
import kotlin.test.assertEquals

class Day8Test {

    private var day = Day8(Aoc())

    @Test
    fun testPart1Output() = assertEquals(1134, day.part1())

    @Test
    fun testPart2Output() = assertEquals(1205, day.part2())
}
