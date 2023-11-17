package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.PuzzleMeta
import nl.sbmf21.aoc22.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * This one is up to chance... since it tries 500_000 times and
 * takes the highest number, based on a bunch of random decisions.
 * It *could* fail.
 */
class Day19Test {

    @Test
    fun testInput() {
        testDay(Day19::class.java, 1681, 5394)
    }

    @Test
    fun testExample() {
        val day = PuzzleMeta(Day19::class.java).build(true, null)
        assertEquals(33, day.part1())
    }
}