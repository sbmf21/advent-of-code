package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day16 : ADay() {

    private val packet = BitsPacket(input[0].map { "$it".toInt(16).toString(2).padStart(4, '0') }.joinToString(""))

    override fun part1() = packet.version()
    override fun part2() = packet.exec()

    private class BitsPacket(input: String, start: Int = 0) {
        private val version = input.substring(start, start + 3).toInt(2)
        private val typeId = input.substring(start + 3, start + 6).toInt(2)
        private val end: Int
        private val packets = mutableListOf<BitsPacket>()
        private var value = 0L

        init {
            var end = start + 6

            if (typeId == 4) value = input.substring(end).chunked(5)
                .run { subList(0, indexOfFirst { it[0] == '0' } + 1).joinToString("") { end += 5; it.substring(1) } }
                .toLong(2)
            else end = (end++).run {
                var next = this
                if (input[next++] == '0') {
                    next += 15
                    val max = next + input.substring(end, next).toInt(2)
                    while (next < max) next = add(input, next)
                } else {
                    next += 11
                    for (i in 0 until input.substring(end, next).toLong(2)) next = add(input, next)
                }; next
            }

            this.end = end
        }

        private fun add(input: String, next: Int) = BitsPacket(input, next).also { packets.add(it) }.end

        fun exec(): Long = packets.map { it.exec() }.run {
            when (typeId) {
                0 -> sum()
                1 -> fold(1L) { acc, p -> acc * p }
                2 -> minOf { it }
                3 -> maxOf { it }
                4 -> value
                5 -> if (this[0] > this[1]) 1L else 0L
                6 -> if (this[0] < this[1]) 1L else 0L
                7 -> if (this[0] == this[1]) 1L else 0L
                else -> 0L
            }
        }

        fun version(): Int = packets.sumOf { it.version() } + version
    }
}