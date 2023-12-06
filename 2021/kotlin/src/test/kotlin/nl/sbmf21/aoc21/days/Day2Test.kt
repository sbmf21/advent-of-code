package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun testInput() = testDay(Day2::class.java, 1_690_020, 1_408_487_760)

    @Test
    fun testExample() = testDay(Day2::class.java, 150, 900, true)
}