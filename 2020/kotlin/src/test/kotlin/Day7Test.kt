import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.days.Day7
import org.junit.Test
import kotlin.test.assertEquals

class Day7Test {

    private var day = Day7(Aoc())

    @Test
    fun testPart1Output() = assertEquals(112, day.part1())

    @Test
    fun testPart2Output() = assertEquals(6260, day.part2())
}
