import nl.sbmf21.aoc21.Aoc
import nl.sbmf21.aoc21.days.Day2
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {

    private var day = Day2(Aoc(), 2)

    @Test
    fun testPart1Output() = assertEquals(1690020, day.part1())

    @Test
    fun testPart2Output() = assertEquals(1408487760, day.part2())
}
