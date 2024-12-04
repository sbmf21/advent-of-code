package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day22Test {

    @Test
    fun testInput() {
        testDay(Day22::class.java, 149_250, 12462)
    }

    @Test
    fun testExample() {
        testDay(Day22::class.java, 6032, 5031, true)
    }
}