import nl.sbmf21.aoc21.Aoc
import nl.sbmf21.aoc21.days.Day4
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Test {

    private var day = Day4(Aoc(), 4)

    @Test
    fun testPart1Output() = assertEquals(55770, day.part1())

    @Test
    fun testPart2Output() = assertEquals(2980, day.part2())
}
