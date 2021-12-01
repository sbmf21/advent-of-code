import nl.sbmf21.aoc21.Aoc
import nl.sbmf21.aoc21.days.Day1
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {

    private var day = Day1(Aoc())

    @Test
    fun testPart1Output() = assertEquals(1759, day.part1())

    @Test
    fun testPart2Output() = assertEquals(1805, day.part2())
}
