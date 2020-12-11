import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.days.Day11
import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {

    private var day = Day11(Aoc())

    @Test
    fun testPart1Output() = assertEquals(2468, day.part1())

    @Test
    fun testPart2Output() = assertEquals(2214, day.part2())
}
