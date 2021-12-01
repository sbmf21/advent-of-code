import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day5
import org.junit.Test
import kotlin.test.assertEquals

class Day5Test {

    private var day = Day5(Aoc(), 5)

    @Test
    fun testPart1Output() = assertEquals(922, day.part1())

    @Test
    fun testPart2Output() = assertEquals(747, day.part2())
}
