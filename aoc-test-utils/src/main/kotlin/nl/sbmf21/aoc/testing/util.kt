package nl.sbmf21.aoc.testing

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.FinalDay
import nl.sbmf21.aoc.common.Puzzle
import nl.sbmf21.aoc.common.PuzzleMeta
import kotlin.test.assertEquals

fun <A : Any, B : Any> testDay(
    clazz: Class<out Day>,
    part1: A,
    part2: B,
    example: Boolean = false,
    filename: String? = null,
) {
    val day = buildPuzzle(clazz, example, filename)

    assertEquals(part1, day.part1())
    assertEquals(part2, day.part2())
}

fun <A : Any> testFinalDay(
    clazz: Class<out FinalDay>,
    solution: A,
    example: Boolean = false,
    filename: String? = null,
) {
    val day = buildPuzzle(clazz, example, filename)

    assertEquals(solution, day.solution())
}

fun <T : Puzzle> buildPuzzle(clazz: Class<out T>, example: Boolean = false, filename: String? = null): T =
    PuzzleMeta(clazz).build(example, filename)

fun <T : Puzzle> buildWithInput(clazz: Class<out T>, input: List<String>): T =
    PuzzleMeta(clazz).build(input)