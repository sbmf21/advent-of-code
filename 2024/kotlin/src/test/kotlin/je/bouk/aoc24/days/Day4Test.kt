package je.bouk.aoc24.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun testInput() = testDay(Day4::class.java, 2378, 1796)

    @Test
    fun testExample() = testDay(Day4::class.java, 18, 9, true)
}