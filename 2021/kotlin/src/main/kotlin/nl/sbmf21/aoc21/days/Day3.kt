package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day3(input: List<String>) : ADay(input) {

    private val lines = input.toList() // caching the lines

    override fun part1() = lines[0]
        .mapIndexed { i, _ -> c(i) }.map { p -> Pair(m(p) { a, b -> a <= b }, m(p) { a, b -> a > b }) }
        .fold(Pair("", "")) { a, p -> Pair(a.first + p.first, a.second + p.second) }.run { combine(this) }

    override fun part2() = Pair(f { a, b -> a <= b }[0], f { a, b -> a > b }[0]).run { combine(this) }

    private fun c(i: Int, l: List<String> = lines) = Pair(l.filter { it[i] == '0' }.size, l.filter { it[i] == '1' }.size)

    private fun f(l: List<String> = lines, i: Int = 0, f: (a: Int, b: Int) -> Boolean): List<String> =
        l.filter { it[i] == m(c(i, l), f) }.run { if (this.size > 1) f(this, i + 1, f) else this }
}

private fun m(p: Pair<Int, Int>, f: (a: Int, b: Int) -> Boolean) = if (f(p.first, p.second)) '1' else '0'
private fun combine(p: Pair<String, String>) = parse(p.first) * parse(p.second)
private fun parse(n: String) = Integer.parseInt(n, 2)
