import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.days.Day12
import org.junit.Test
import kotlin.test.assertEquals

class Day12Test {

    private var day = Day12(Aoc())

    @Test
    fun testPart1Output() = assertEquals(1710, day.part1())

    @Test
    fun testPart2Output() = assertEquals(62045, day.part2())
}
