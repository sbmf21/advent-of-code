package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.PuzzleMeta
import nl.sbmf21.aoc22.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun testInput() = testDay(Day9::class.java, 6236, 2449)

    @Test
    fun testExamplePart1() {
        assertEquals(13, PuzzleMeta(Day9::class.java).build(true, "1").part1())
    }

    @Test
    fun testExamplePart2() {
        assertEquals(36, PuzzleMeta(Day9::class.java).build(true, "2").part2())
    }
}