package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.Aoc

class Day4(aoc: Aoc, number: Int) : ADay(aoc, number) {

    private val pattern = Regex("\\d+(,\\d+)+")
    internal val drawn = input
        .filter { it.matches(pattern) }
        .map { it.split(',').map { n -> n.toInt() } }
        .flatten()
    private val boardData = input
        .filter { !it.matches(pattern) }
        .map { it.split(' ').filter { l -> l.isNotBlank() }.map { n -> n.toInt() } }
        .map { it.toMutableList() }
    private val boardSize = boardData.size / boardData.filter { it.isEmpty() }.size
    private var boards = boardData.chunked(boardSize)
        .map { it.filter { l -> l.isNotEmpty() } }
        .map { Board(this, it.toMutableList()) }
        .toMutableList()

    override fun part1(): Int {
        drawn.forEach { d -> boards.forEach { b -> b.rows.forEach { it.remove(d); if (it.isEmpty()) return b.s(d) } } }
        return -1
    }

    override fun part2(): Int {
        var last: Board? = null
        var number = -1

        drawn.forEach { d ->
            boards.iterator().run {
                while (hasNext()) {
                    val b = next()

                    for (it in b.rows) {
                        it.remove(d)

                        if (it.isEmpty()) {
                            remove(); last = b; number = d; break
                        }
                    }
                }
            }
        }

        return last!!.s(number)
    }
}

class Board(private val day: Day4, private val original: MutableList<MutableList<Int>>) {

    val rows = original[0]
        .foldIndexed(mutableListOf<MutableList<Int>>()) { i, a, _ -> a.add(original.map { it[i] }.toMutableList()); a }
        .apply { addAll(original) }

    internal fun s(d: Int) = original.flatten()
        .filter { !day.drawn.subList(0, day.drawn.indexOf(d)).contains(it) }
        .sum() * d
}