import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day1
import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {

    private var day = Day1(Aoc())

    @Test
    fun testPart1Output() = assertEquals(1019904, day.part1())

    @Test
    fun testPart2Output() = assertEquals(176647680, day.part2())
}
