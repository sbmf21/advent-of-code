package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.prod
import kotlin.math.min

class Day13 : Day() {

    private val comparator = PacketCompare()
    private val packets = input.chunked(3).map { Pair(Packet.from(it[0]), Packet.from(it[1])) }
    private val dividers = listOf("[[2]]", "[[6]]").map { Packet.from(it, true) }

    override fun part1() = packets
        .mapIndexed { index, it -> if (comparator.compare(it.first, it.second) == -1) index + 1 else 0 }
        .sum()

    override fun part2() = packets
        .flatMap { listOf(it.first, it.second) }
        .plus(dividers)
        .sortedWith(comparator)
        .mapIndexedNotNull { index, packet -> if (packet.divider) index + 1 else null }
        .prod()

    private class Packet(val parent: Packet? = null, val divider: Boolean = false) :
        MutableList<Any> by mutableListOf() {
        companion object {
            fun from(line: String, divider: Boolean = false): Packet {
                val root = Packet(divider = divider)
                var current: Packet = root
                var sameList = false

                var i = 1
                while (i < line.length) {
                    var c = line[i++]

                    var number = ""
                    while (c in '0'..'9') {
                        number += c
                        c = line[i++]
                    }
                    if (number.isNotEmpty()) current.add(number.toInt())

                    when (c) {
                        '[' -> Packet(parent = if (sameList) current.parent else current).also {
                            sameList = false
                            current.add(it)
                            current = it
                        }

                        ']' -> current.parent?.also { current = it }
                        ',' -> sameList = true
                    }
                }
                return root
            }
        }
    }

    private class PacketCompare : Comparator<List<*>> {
        override fun compare(left: List<*>, right: List<*>): Int {
            for (i in 0 until min(left.size, right.size)) {
                var cLeft = left[i]
                var cRight = right[i]

                if (cLeft is Int && cRight is Int)
                    return if (cLeft < cRight) -1
                    else if (cLeft > cRight) 1
                    else continue
                else if (cLeft is Int) cLeft = listOf(cLeft)
                else if (cRight is Int) cRight = listOf(cRight)

                compare(cLeft as List<*>, cRight as List<*>).also { if (it != 0) return it }
            }
            return left.size.compareTo(right.size)
        }
    }
}