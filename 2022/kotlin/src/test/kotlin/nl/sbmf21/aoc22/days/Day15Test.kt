package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun testInput() {
        Day15.ROW = Day15.DEFAULT_ROW
        Day15.MAX_COORD = Day15.DEFAULT_MAX_COORD

        testDay(Day15::class.java, 5_688_618, 12_625_383_204_261)
    }

    @Test
    fun testExample() {
        Day15.ROW = 10
        Day15.MAX_COORD = 20

        testDay(Day15::class.java, 26, 56_000_011L, true)
    }
}