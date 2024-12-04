package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day10Test {

    @Test
    fun testInput() = testDay(Day10::class.java, 343_863, 2_924_734_236L)

    @Test
    fun testExample() = testDay(Day10::class.java, 26_397, 288_957L, true)
}