package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.PuzzleMeta
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun testInput() = testDay(Day10::class.java, 15140, "BPJAZGAP")

    @Test
    fun testExample() {
        val day = PuzzleMeta(Day10::class.java).build(true)
        assertEquals(13140, day.part1())
        day.part2()
    }
}