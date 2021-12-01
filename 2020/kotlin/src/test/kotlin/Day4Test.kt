import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day4
import org.junit.Test
import kotlin.test.assertEquals

class Day4Test {

    private var day = Day4(Aoc(), 4)

    @Test
    fun testPart1Output() = assertEquals(247, day.part1())

    @Test
    fun testPart2Output() = assertEquals(145, day.part2())
}
