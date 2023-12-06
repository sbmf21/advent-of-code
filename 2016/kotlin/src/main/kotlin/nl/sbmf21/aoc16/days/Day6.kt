package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.common.Day

private typealias D6Count = Pair<Char, Int>
private typealias D6CountList = List<D6Count>

class Day6 : Day() {

    private val counts = input[0].indices
        .map { i -> input.map { it[i] } }
        .map { chars -> chars.map { c -> c to chars.count { it == c } }.distinctBy(D6Count::first) }

    override fun part1() = solve(D6CountList::maxBy)
    override fun part2() = solve(D6CountList::minBy)

    private fun solve(map: D6CountList.((D6Count) -> Int) -> D6Count) = counts
        .joinToString("") { it.map(D6Count::second).first.toString() }
}