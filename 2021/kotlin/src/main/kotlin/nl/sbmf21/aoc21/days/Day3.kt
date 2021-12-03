package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.Aoc

class Day3(aoc: Aoc, number: Int) : ADay(aoc, number) {

    override fun part1(): Number {
        var g = ""; var e = ""

        for (i in 0 until input[0].length) {
            val p = count(i)

            g += if (p.first <= p.second) '1' else '0'
            e += if (p.first > p.second) '1' else '0'
        }

        return combine(g, e)
    }

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

private fun combine(a: String, b: String) = parse(a) * parse(b)
private fun parse(n: String) = Integer.parseInt(n, 2)
