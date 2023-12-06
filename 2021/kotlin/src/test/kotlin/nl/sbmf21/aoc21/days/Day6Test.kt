package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun testInput() = testDay<Long, Long>(Day6::class.java, 374_927, 1_687_617_803_407)

    @Test
    fun testExample() = testDay<Long, Long>(Day6::class.java, 5934, 26_984_457_539, true)
}