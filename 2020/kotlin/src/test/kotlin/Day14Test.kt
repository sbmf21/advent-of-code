import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc20.days.Day14
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {

    private var day = Day14(Aoc(), 14)

    @Test
    fun testPart1Output() = assertEquals(17481577045893, day.part1())

    @Test
    fun testPart2Output() = assertEquals(4160009892257, day.part2())
}
