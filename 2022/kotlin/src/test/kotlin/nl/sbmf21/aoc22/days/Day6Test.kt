package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun testInput() = testDay(Day6::class.java, 1802, 3551)

    @Test
    fun testExample() = mapOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to (7 to 19),
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to (5 to 23),
        "nppdvjthqldpwncqszvftbrmjlhg" to (6 to 23),
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to (10 to 29),
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to (11 to 26),
    ).forEach { (data, end) ->
        val day = buildWithInput(Day6::class.java, listOf(data))
        assertEquals(end.first, day.part1())
        assertEquals(end.second, day.part2())
    }
}