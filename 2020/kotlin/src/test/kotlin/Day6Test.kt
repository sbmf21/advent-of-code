import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day6
import org.junit.Test
import kotlin.test.assertEquals

class Day6Test {

    private var day = Day6(Aoc())

    @Test
    fun testPart1Output() = assertEquals(6903, day.part1())

    @Test
    fun testPart2Output() = assertEquals(3493, day.part2())
}
