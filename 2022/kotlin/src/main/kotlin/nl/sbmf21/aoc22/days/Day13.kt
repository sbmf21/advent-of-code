package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.prod
import kotlin.math.min

class Day13(input: List<String>) : ADay(input) {

    private val packets = input.chunked(3).map { Pair(read(it[0]), read(it[1])) }

    override fun part1() = packets
        .asSequence()
        .mapIndexed { index, it -> if (compare(it.first.input, it.second.input) == -1) index + 1 else 0 }
        .sum()

    override fun part2() = packets
        .asSequence()
        .map { listOf(it.first, it.second) }
        .flatten()
        .toMutableList()
        .also { it.addAll(listOf(read("[[2]]", true), read("[[6]]", true))) }
        .sortedWith(PacketCompare())
        .mapIndexedNotNull { index, packet -> if (packet.divider) index + 1 else null }
        .prod()
}

private class PacketCompare : Comparator<Packet> {
    override fun compare(left: Packet, right: Packet) = compare(left.input, right.input)
}

private fun compare(left: List<*>, right: List<*>): Int {

    for (i in 0 until min(left.size, right.size)) {
        var cLeft = left[i]
        var cRight = right[i]

        if (cLeft is Packet) cLeft = cLeft.input
        if (cRight is Packet) cRight = cRight.input

        if (cLeft is Int && cRight is Int)
            return if (cLeft < cRight) -1
            else if (cLeft > cRight) 1
            else continue
        if (cRight is Int) cRight = listOf(cRight)
        else if (cLeft is Int) cLeft = listOf(cLeft)

        val compare = compare(cLeft as List<*>, cRight as List<*>)
        if (compare != 0) return compare
    }

    return left.size.compareTo(right.size)
}

private fun read(line: String, divider: Boolean = false): Packet {

    var i = 1

    val root = Packet(divider = divider)
    var current: Packet = root

    var sameList = false

    while (i < line.length) {
        var c = line[i]

        var number = ""
        while (c in '0'..'9') {
            number += c
            c = line[++i]
        }

        if (number.isNotEmpty()) current.input.add(number.toInt())

        when (c) {
            '[' -> {
                val packet = Packet(parent = if (sameList) current.parent else current)
                sameList = false
                current.input.add(packet)
                current = packet
            }

            ']' -> {
                val parent = current.parent
                if (parent != null) current = parent
            }

            ',' -> sameList = true
        }

        i++
    }
    return root
}

private class Packet(val parent: Packet? = null, val divider: Boolean = false) {
    val input = mutableListOf<Any>()
}
