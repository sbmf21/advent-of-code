import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day13
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {

    private var day = Day13(Aoc(), 13)

    @Test
    fun testPart1Output() = assertEquals(156, day.part1())

    @Test
    fun testPart2Output() = assertEquals(404517869995362, day.part2())
}
