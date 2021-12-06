import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day8
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {

    private var day = Day8(Aoc(), 8)

    @Test
    fun testPart1Output() = assertEquals(1134, day.part1())

    @Test
    fun testPart2Output() = assertEquals(1205, day.part2())
}
