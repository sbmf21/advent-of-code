import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day15
import org.junit.Test
import kotlin.test.assertEquals

class Day15Test {

    private var day = Day15(Aoc(), 15)

    @Test
    fun testPart1Output() = assertEquals(1009, day.part1())

    @Test
    fun testPart2Output() = assertEquals(62714, day.part2())
}
