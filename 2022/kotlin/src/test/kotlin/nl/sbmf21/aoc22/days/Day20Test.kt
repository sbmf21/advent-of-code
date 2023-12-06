package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day20Test {

    @Test
    fun testInput() {
        testDay(Day20::class.java, 1591L, 14_579_387_544_492L)
    }

    @Test
    fun testExample() {
        testDay(Day20::class.java, 3L, 1_623_178_306L, true)
    }
}