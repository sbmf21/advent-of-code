package je.bouk.aoc24.days

import nl.sbmf21.aoc.common.PuzzleMeta
import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {

    @Test
    fun testInput() = testDay(Day8::class.java, 280, 958)

    @Test
    fun testExample() = testDay(Day8::class.java, 14, 34, true)

    @Test
    fun testExample2() = assertEquals(9, PuzzleMeta(Day8::class.java).build(true, "T").part2())
}