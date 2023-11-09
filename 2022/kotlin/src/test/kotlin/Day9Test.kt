import nl.sbmf21.aoc.common.DayMeta
import nl.sbmf21.aoc22.days.Day9
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun testInput() = testDay(Day9::class.java, 6236, 2449)

    @Test
    fun testExamplePart1() {
        assertEquals(13, DayMeta(Day9::class.java).build(true, "1").part1())
    }

    @Test
    fun testExamplePart2() {
        assertEquals(36, DayMeta(Day9::class.java).build(true, "2").part2())
    }
}