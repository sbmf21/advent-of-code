package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day13Test {

    // 4401
    // 8185
    // 8784
    // 15095
    // 18300
    // 19396
    // 20974
    // 22674
    // 40598
    // 43099
    // 44143

    @Test
    fun testInput() = testDay(Day13::class.java, 34918, -1)

    @Test
    fun testExample() = testDay(Day13::class.java, 405, -1 /* 400 */, true)
}