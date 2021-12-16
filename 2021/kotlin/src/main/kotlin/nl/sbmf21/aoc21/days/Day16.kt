package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day16(input: List<String>) : ADay(input) {

    private val initial = input[0].map { Integer.toBinaryString("$it".toInt(16)).padStart(4, '0') }.joinToString("")
    private val packet = decryptPacket()

    override fun part1() = packet.version()

    override fun part2() = packet.exec()

    private fun decryptPacket(line: String = initial): BitsPacket {
        val version = line.substring(0, 3).toInt(2)
        val typeId = line.substring(3, 6).toInt(2)
        var index = 6

        if (typeId == 4) {
            var number = ""

            while (true) {
                val start = line.substring(index, index + 1)
                number += line.substring(index + 1, index + 5); index += 5
                if (start == "0") break
            }

            return BitsPacket(version, typeId, index, value = number.toLong(2))
        } else {
            val packets = mutableListOf<BitsPacket>()
            var next = ++index

            if (line[6] == '0') {
                next += 15
                val end = next + line.substring(index, next).toInt(2)
                while (next < end) next += add(line, next, packets)
            } else {
                next += 11
                for (i in 0 until line.substring(index, next).toLong(2)) next += add(line, next, packets)
            }

            return BitsPacket(version, typeId, next, packets = packets)
        }
    }

    private fun add(line: String, nextIndex: Int, subPackets: MutableList<BitsPacket>) =
        decryptPacket(line.substring(nextIndex)).run { subPackets.add(this); end }
}

internal class BitsPacket(
    private val version: Int,
    private val typeId: Int,
    val end: Int,
    private val packets: List<BitsPacket> = listOf(),
    private val value: Long = 0L,
) {
    fun exec(): Long = packets.map { it.exec() }.run {
        when (typeId) {
            0 -> sum()
            1 -> fold(1L) { acc, p -> acc * p }
            2 -> minOf { it }
            3 -> maxOf { it }
            4 -> value
            5 -> (this[0] > this[1]).toLong()
            6 -> (this[0] < this[1]).toLong()
            7 -> (this[0] == this[1]).toLong()
            else -> 0L
        }
    }

    fun version(): Int = packets.sumOf { it.version() } + version
}

internal fun Boolean.toLong() = if (this) 1L else 0L
