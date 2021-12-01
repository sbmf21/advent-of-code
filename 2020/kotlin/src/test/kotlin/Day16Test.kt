import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day16
import org.junit.Test
import kotlin.test.assertEquals

class Day16Test {

    private var day = Day16(Aoc())

    @Test
    fun testPart1Output() = assertEquals(27850, day.part1())

    @Test
    fun testPart2Output() = assertEquals(0, day.part2())
}