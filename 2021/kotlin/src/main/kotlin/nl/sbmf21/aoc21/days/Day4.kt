package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.iterated
import nl.sbmf21.aoc.common.mapToInts

class Day4 : Day() {

    private val drawn = input[0].split(',').map { it.toInt() }
    private var boards = input.subList(2, input.size).asSequence().filter { it.isNotBlank() }
        .map { it.split(' ').filter { l -> l.isNotBlank() }.mapToInts() }
        .map { it.toMutableList() }.chunked(5)
        .map { Board(drawn, it.toMutableList()) }
        .toMutableList()

    override fun part1(): Int {
        drawn.forEach { d -> boards.forEach { b -> b.rows.forEach { if (it.remove(d) && it.isEmpty()) return b.s(d) } } }
        return -1
    }

    override fun part2(): Int {
        drawn.forEach { d ->
            boards.iterated { iter, b ->
                b.rows.forEach { it.remove(d) }
                if (b.rows.any { it.isEmpty() }) iter.remove()
                if (boards.isEmpty()) return b.s(d)
            }
        }
        return -1
    }

    private class Board(private val drawn: List<Int>, private val original: MutableList<MutableList<Int>>) {

        val rows = original[0]
            .foldIndexed(mutableListOf<MutableList<Int>>()) { i, a, _ ->
                a.add(original.map { it[i] }.toMutableList()); a
            }
            .apply { addAll(original) }

        fun s(d: Int) = original.flatten().filter { !drawn.subList(0, drawn.indexOf(d)).contains(it) }.sum() * d
    }
}