import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.days.Day2
import org.junit.Test
import kotlin.test.assertEquals

class Day2Test {

    private var day = Day2(Aoc())

    @Test
    fun testPart1Output() = assertEquals(515, day.part1())

    @Test
    fun testPart2Output() = assertEquals(711, day.part2())
}
