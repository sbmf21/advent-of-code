package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc23.TODO
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

class Day18 : Day() {

    private val instructions = input
        .map { it.split(" ", limit = 3) }
        .map { (dir, num, hex) -> Instruction(Direction(dir[0]), num.toInt(), hex.substring(1, hex.length - 1)) }

    override fun part1(): Any {

        var start = 0 by 0
        val filled = mutableSetOf<Vector2i>()

        instructions.forEach { (direction, meters, _) ->
            for (i in 1..meters) filled += start + (direction.vec * i)
            start = start + (direction.vec * meters)
        }

        val minX = filled.minOf(Vector2i::x)
        val maxX = filled.maxOf(Vector2i::x)
        val xRange = minX..maxX

        val minY = filled.minOf(Vector2i::y)
        val maxY = filled.maxOf(Vector2i::y)
        val yRange = minY..maxY

        val outside = mutableSetOf<Vector2i>()
        val toCheck = mutableListOf<Vector2i>()

        for (x in xRange) {
            (x by minY).let { if (it !in filled) toCheck += it }
            (x by maxY).let { if (it !in filled) toCheck += it }
        }

        for (y in yRange) {
            (minX by y).let { if (it !in filled) toCheck += it }
            (maxX by y).let { if (it !in filled) toCheck += it }
        }

        outside.addAll(toCheck)

        while (toCheck.isNotEmpty()) {
            val check = toCheck.removeFirst()
            Direction.entries
                .map { it.vec + check }
                .filterNot { it in outside || it in filled }
                .filter { it.x in minX..maxX && it.y in minY..maxY }
                .forEach {
                    outside += it
                    toCheck += it
                }
        }

        val x = maxX - minX + 1
        val y = maxY - minY + 1

        return x * y - outside.size
    }

    override fun part2(): Any {

        val instructions = instructions.map(Instruction::real)

//        val corners = buildList {
//            var start = 0 by 0
//            instructions.forEach {
//                val end = start + it.direction.vec * it.meters
//                this += start to end
//                start = end
//            }
//        }
//
//        val minX = corners.minOf { it.second.x }
//        val maxX = corners.maxOf { it.second.x }
//        val minY = corners.minOf { it.second.y }
//        val maxY = corners.maxOf { it.second.y }
//
//        var count = 0L
//
//        for (i in minY..maxY) {
//            val crossing = corners.filter { i in it.first.y..it.second.y }
//
//        }

//        println(minX)
//        println(maxX)
//        println(minY)
//        println(maxY)

//        0
//        1186328
//        0
//        1186328

        return TODO
    }

    private data class Instruction(val direction: Direction, val meters: Int, val hex: String) {
        fun real() = Instruction(Direction(hex.substring(6, 7).toInt()), hex.substring(1, 6).toInt(16), hex)
    }

    private enum class Direction(val vec: Vector2i) {
        RIGHT(1 by 0),
        DOWN(0 by 1),
        LEFT(-1 by 0),
        UP(0 by -1);

        val char = name[0]

        operator fun inc() = entries[(ordinal + 1).mod(entries.size)]
        operator fun dec() = entries[(ordinal - 1).mod(entries.size)]

        companion object {
            operator fun invoke(char: Char) = entries.first { it.char == char }
            operator fun invoke(int: Int) = entries[int]
        }
    }
}