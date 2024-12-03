package je.bouk.aoc24.days

import nl.sbmf21.aoc.common.PuzzleMeta
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun testInput() = testDay(Day3::class.java, 181_345_830, 98_729_041)

    @Test
    fun testExamplePart1() = assertEquals(161, PuzzleMeta(Day3::class.java).build(true, "1").part1())

    @Test
    fun testExamplePart2() = assertEquals(48, PuzzleMeta(Day3::class.java).build(true, "2").part2())
}