package je.bouk.aoc24.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun testInput() = testDay(Day2::class.java, 624, 658)

    @Test
    fun testExample() = testDay(Day2::class.java, 2, 4, true)
}