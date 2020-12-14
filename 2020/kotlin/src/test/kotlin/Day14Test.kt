import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.days.Day14
import org.junit.Test
import kotlin.test.assertEquals

class Day14Test {

    private var day = Day14(Aoc())

    @Test
    fun testPart1Output() = assertEquals(17481577045893, day.part1())

    @Test
    fun testPart2Output() = assertEquals(4160009892257, day.part2())
}
