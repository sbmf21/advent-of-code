import nl.sbmf21.aoc21.Aoc
import nl.sbmf21.aoc21.days.Day3
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    private var day = Day3(Aoc(), 3)

    @Test
    fun testPart1Output() = assertEquals(4118544, day.part1())

    @Test
    fun testPart2Output() = assertEquals(3832770, day.part2())
}
