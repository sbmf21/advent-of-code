package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day17Test {

    @Test
    fun testInput() {
        testDay(Day17::class.java, 3114L, 1_540_804_597_682L)
    }

    @Test
    fun testExample() {
        testDay(Day17::class.java, 3068L, 1_514_285_714_288L, true)
    }
}