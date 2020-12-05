import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.days.Day3
import org.junit.Test
import kotlin.test.assertEquals

class Day3Test {

    private var day = Day3(Aoc())

    @Test
    fun testPart1Output() = assertEquals(191, day.part1())

    @Test
    fun testPart2Output() = assertEquals(1478615040, day.part2())
}
