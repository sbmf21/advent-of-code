package je.bouk.aoc24.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.splitToInts
import kotlin.math.abs

class Day1 : Day() {

    val left: List<Int>
    val right: List<Int>

    init {
        input.map {
            val (left, right) = it.splitToInts("   ")
            left to right
        }.run {
            left = map { it.first }
            right = map { it.second }
        }
    }

    override fun part1() = solve(left.sorted(), right.sorted()) { i, left, right -> abs(left - right[i]) }
    override fun part2() = solve { i, left, right -> left * right.count { it == left } }

    private fun solve(
        left: List<Int> = this.left,
        right: List<Int> = this.right,
        solve: (i: Int, left: Int, right: List<Int>) -> Int,
    ) = left.mapIndexed { i, left -> solve(i, left, right) }.sum()
}