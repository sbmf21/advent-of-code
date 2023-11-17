package nl.sbmf21.aoc21

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.FinalDay
import nl.sbmf21.aoc.common.PuzzleMeta
import kotlin.test.assertEquals

fun <A : Any, B : Any> testDay(
    clazz: Class<out Day>,
    part1: A,
    part2: B,
    example: Boolean = false,
    filename: String? = null
) {
    val day = PuzzleMeta(clazz).build(example, filename)

    assertEquals(part1, day.part1())
    assertEquals(part2, day.part2())
}

fun <A : Any> testFinalDay(
    clazz: Class<out FinalDay>,
    solution: A,
    example: Boolean = false,
    filename: String? = null
) {
    val day = PuzzleMeta(clazz).build(example, filename)

    assertEquals(solution, day.solution())
}

fun buildWithInput(clazz: Class<out Day>, input: List<String>) = PuzzleMeta(clazz).build(input)