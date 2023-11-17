package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.PuzzleMeta
import nl.sbmf21.aoc21.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day22Test {

    @Test
    fun testInput() = testDay(Day22::class.java, 647_076, 1_233_304_599_156_793L)

    @Test
    fun testExample() = assertEquals(39, PuzzleMeta(Day22::class.java).build(true).part1())

    @Test
    fun testExampleLarge() = assertEquals(590_784, PuzzleMeta(Day22::class.java).build(true, "large").part1())

    @Test
    fun testExampleHuge() = assertEquals(2_758_514_936_282_235, PuzzleMeta(Day22::class.java).build(true, "huge").part2())
}