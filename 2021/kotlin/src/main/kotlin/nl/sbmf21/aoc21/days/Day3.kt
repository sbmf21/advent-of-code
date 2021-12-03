package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.Aoc

class Day3(aoc: Aoc, number: Int) : ADay(aoc, number) {

    override fun part1() = input[0]
        .mapIndexed { i, _ -> c(i) }
        .map { p -> Pair(m(p) { a, b -> a <= b }, m(p) { a, b -> a > b }) }
        .fold(Pair("", "")) { a, p -> Pair(a.first + p.first, a.second + p.second) }.run { combine(this) }

    override fun part2() = Pair(filter { a, b -> a <= b }[0], filter { a, b -> a > b }[0]).run { combine(this) }

    private fun c(i: Int, l: List<String> = input) = Pair(
        l.filter { it[i] == '0' }.size,
        l.filter { it[i] == '1' }.size
    )

    private fun filter(l: List<String> = input, i: Int = 0, f: (a: Int, b: Int) -> Boolean): List<String> = l
        .filter { it[i] == m(c(i, l), f) }
        .run { if (this.size > 1) filter(this, i + 1, f) else this }
}

private fun m(p: Pair<Int, Int>, f: (a: Int, b: Int) -> Boolean) = if (f(p.first, p.second)) '1' else '0'
private fun combine(p: Pair<String, String>) = parse(p.first) * parse(p.second)
private fun parse(n: String) = Integer.parseInt(n, 2)
