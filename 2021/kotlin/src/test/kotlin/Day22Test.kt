import nl.sbmf21.aoc.common.DayMeta
import nl.sbmf21.aoc21.days.Day22
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day22Test {

    @Test
    fun testInput() = testDay(Day22::class.java, 647_076, -1L)

    @Test
    fun testExample() = assertEquals(39, DayMeta(Day22::class.java).build(true).part1())

    @Test
    fun testExampleLarge() = assertEquals(590_784, DayMeta(Day22::class.java).build(true, "large").part1())

    @Test
    fun testExampleHuge() =
        assertEquals(-1L /*2_758_514_936_282_235*/, DayMeta(Day22::class.java).build(true, "huge").part2())
}