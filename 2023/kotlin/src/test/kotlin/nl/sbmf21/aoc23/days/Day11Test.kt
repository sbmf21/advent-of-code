package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day11Test {

    @Test
    fun testInput() = testDay(Day11::class.java, 9_918_828L, 692_506_533_832L)

    @Test
    fun testExample() = testDay(Day11::class.java, 374L, 82_000_210L, true)
}