package nl.sbmf21.aoc22

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.DayMeta
import kotlin.test.assertEquals

fun <A : Any, B : Any> testDay(
    clazz: Class<out Day>,
    part1: A,
    part2: B,
    example: Boolean = false,
    filename: String? = null
) {
    val day = DayMeta(clazz).build(example, filename)

    assertEquals(part1, day.part1())
    assertEquals(part2, day.part2())
}

fun buildWithInput(clazz: Class<out Day>, input: List<String>) = DayMeta(clazz).build(input)