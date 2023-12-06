package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day16Test {

    @Test
    fun testInput() {
        testDay(Day16::class.java, 1647, 2169)
    }

    @Test
    fun testExample() {
        testDay(Day16::class.java, 1651, 1707, true)
    }
}