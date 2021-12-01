import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day3
import org.junit.Test
import kotlin.test.assertEquals

class Day3Test {

    private var day = Day3(Aoc(), 3)

    @Test
    fun testPart1Output() = assertEquals(191, day.part1())

    @Test
    fun testPart2Output() = assertEquals(1478615040, day.part2())
}
