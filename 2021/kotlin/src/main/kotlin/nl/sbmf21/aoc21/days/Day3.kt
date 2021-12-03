package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.Aoc

class Day3(aoc: Aoc, number: Int) : ADay(aoc, number) {

    override fun part1() = combine(input[0]
        .mapIndexed { i, _ ->
            val p = count(i)
            Pair(if (p.first <= p.second) '1' else '0', if (p.first > p.second) '1' else '0')
        }.fold(Pair("", "")) { a, p -> Pair(a.first + p.first, a.second + p.second) })

    override fun part2() = combine(
        filter { a, b -> a <= b }[0],
        filter { a, b -> a > b }[0]
    )

    private fun count(i: Int, l: List<String> = input) = Pair(
        l.filter { it[i] == '0' }.size,
        l.filter { it[i] == '1' }.size
    )

    private fun filter(l: List<String> = input, i: Int = 0, f: (a: Int, b: Int) -> Boolean): List<String> {
        val p = count(i, l)
        val filtered = l.filter { it[i] == if (f(p.first, p.second)) '1' else '0' }
        return if (filtered.size > 1) filter(filtered, i + 1, f) else filtered
    }
}

private fun combine(p: Pair<String, String>) = combine(p.first, p.second)
private fun combine(a: String, b: String) = parse(a) * parse(b)
private fun parse(n: String) = Integer.parseInt(n, 2)
