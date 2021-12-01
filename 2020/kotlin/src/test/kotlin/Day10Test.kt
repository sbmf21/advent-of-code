import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day10
import org.junit.Test
import kotlin.test.assertEquals

class Day10Test {

    private var day = Day10(Aoc(), 10)

    @Test
    fun testPart1Output() = assertEquals(2738, day.part1())

    @Test
    fun testPart2Output() = assertEquals(74049191673856, day.part2())
}
