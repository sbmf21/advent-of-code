package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day16(input: List<String>) : ADay(input) {

    private val initial = input[0]
        .map { Integer.toBinaryString("$it".toInt(16)).run { padStart(4, '0') } }
        .joinToString("")

    override fun part1() = getVersion(decryptPacket())

    override fun part2() = decryptPacket().exec()

    private fun getVersion(packet: Packet<Any>): Int =
        (if (packet is OperatorPacket) packet.subPackets.sumOf { getVersion(it) } else 0) + packet.version

    private fun decryptPacket(line: String = initial): Packet<Any> {
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

            return LiteralPacket(version, number.bin(), index)
        } else {
            val subPackets = mutableListOf<Packet<Any>>()
            var nextIndex = ++index

            if (line[6] == '0') {
                nextIndex += 15
                val end = nextIndex + line.substring(index, nextIndex).toInt(2)
                while (nextIndex < end) nextIndex += add(line, nextIndex, subPackets)
            } else {
                nextIndex += 11
                for (i in 0 until line.substring(index, nextIndex).bin()) nextIndex += add(line, nextIndex, subPackets)
            }

            return when (typeId) {
                0 -> SumPacket(version, subPackets, nextIndex)
                1 -> ProductPacket(version, subPackets, nextIndex)
                2 -> MinimumPacket(version, subPackets, nextIndex)
                3 -> MaximumPacket(version, subPackets, nextIndex)
                5 -> GreaterThanPacket(version, subPackets, nextIndex)
                6 -> LessThanPacket(version, subPackets, nextIndex)
                7 -> EqualToPacket(version, subPackets, nextIndex)
                else -> throw IllegalStateException()
            }
        }
    }

    private fun add(line: String, nextIndex: Int, subPackets: MutableList<Packet<Any>>) =
        decryptPacket(line.substring(nextIndex)).run { subPackets.add(this); lastIndex }
}

internal fun String.bin() = toLong(2)

internal abstract class Packet<out T : Any>(val version: Int, val value: T, val lastIndex: Int) {
    abstract fun exec(): Long
}

internal class LiteralPacket(version: Int, private val number: Long, lastIndex: Int) :
    Packet<Number>(version, number, lastIndex) {

    override fun exec() = number
}

internal abstract class OperatorPacket(version: Int, val subPackets: List<Packet<Any>>, lastIndex: Int) :
    Packet<List<Packet<Any>>>(version, subPackets, lastIndex)

internal class SumPacket(version: Int, subPackets: List<Packet<Any>>, lastIndex: Int) :
    OperatorPacket(version, subPackets, lastIndex) {

    override fun exec() = subPackets.sumOf { it.exec() }
}

internal class ProductPacket(version: Int, subPackets: List<Packet<Any>>, lastIndex: Int) :
    OperatorPacket(version, subPackets, lastIndex) {

    override fun exec() = subPackets.map { it.exec() }.fold(1L) { acc, p -> acc * p }
}

internal class MinimumPacket(version: Int, subPackets: List<Packet<Any>>, lastIndex: Int) :
    OperatorPacket(version, subPackets, lastIndex) {

    override fun exec() = subPackets.minOf { it.exec() }
}

internal class MaximumPacket(version: Int, subPackets: List<Packet<Any>>, lastIndex: Int) :
    OperatorPacket(version, subPackets, lastIndex) {

    override fun exec() = subPackets.maxOf { it.exec() }
}

internal abstract class DualOperatorPacket(version: Int, subPackets: List<Packet<Any>>, lastIndex: Int) :
    OperatorPacket(version, subPackets, lastIndex) {

    override fun exec(): Long {
        if (subPackets.size != 2) throw IllegalStateException("Found illegal sub packet size")

        return if (calc(subPackets[0].exec(), subPackets[1].exec())) 1 else 0
    }

    abstract fun calc(left: Long, right: Long): Boolean;
}

internal class GreaterThanPacket(version: Int, subPackets: List<Packet<Any>>, lastIndex: Int) :
    DualOperatorPacket(version, subPackets, lastIndex) {

    override fun calc(left: Long, right: Long) = left > right
}

internal class LessThanPacket(version: Int, subPackets: List<Packet<Any>>, lastIndex: Int) :
    DualOperatorPacket(version, subPackets, lastIndex) {

    override fun calc(left: Long, right: Long) = left < right
}

internal class EqualToPacket(version: Int, subPackets: List<Packet<Any>>, lastIndex: Int) :
    DualOperatorPacket(version, subPackets, lastIndex) {

    override fun calc(left: Long, right: Long) = left == right
}
