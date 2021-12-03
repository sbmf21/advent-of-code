package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.Aoc

class Day3(aoc: Aoc, number: Int) : ADay(aoc, number) {

    override fun part1() = input[0].mapIndexed { i, _ -> count(i) }
        .map { p -> Pair(c(p) { a, b -> a <= b }, c(p) { a, b -> a > b }) }
        .fold(Pair("", "")) { a, p -> Pair(a.first + p.first, a.second + p.second) }
        .run { combine(this) }

    override fun part2() = Pair(filter { a, b -> a <= b }[0], filter { a, b -> a > b }[0]).run { combine(this) }

    private fun count(i: Int, l: List<String> = input) =
        Pair(l.filter { it[i] == '0' }.size, l.filter { it[i] == '1' }.size)

    private fun filter(l: List<String> = input, i: Int = 0, f: (a: Int, b: Int) -> Boolean): List<String> {
        val filtered = l.filter { it[i] == c(count(i, l), f) }
        return if (filtered.size > 1) filter(filtered, i + 1, f) else filtered
    }
}

private fun c(p: Pair<Int, Int>, f: (a: Int, b: Int) -> Boolean) = if (f(p.first, p.second)) '1' else '0'
private fun combine(p: Pair<String, String>) = parse(p.first) * parse(p.second)
private fun parse(n: String) = Integer.parseInt(n, 2)
