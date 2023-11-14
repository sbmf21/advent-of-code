package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc22.testDay
import org.junit.jupiter.api.Test

class Day24Test {

    @Test
    fun testInput() {
        testDay(Day24::class.java, 290, 842)
    }

    @Test
    fun testExample() {
        testDay(Day24::class.java, 18, 54, true)
    }
}