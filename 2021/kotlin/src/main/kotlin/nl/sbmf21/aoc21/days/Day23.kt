package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.math.Vector2i

class Day23(input: List<String>) : ADay(input) {

    private val direGram = input
        .mapIndexed { y, it ->
            it.toCharArray().mapIndexed { x, c ->
                when (c) {
                    '#' -> Wall()
                    '.' -> Floor(Vector2i(x, y))
                    in AmphipodType.types -> Floor(Vector2i(x, y), Amphipod(AmphipodType.fromType(c)))
                    else -> null
                }
            }
        }.also { direGram ->
            direGram.forEach { r ->
                r.filterIsInstance(Floor::class.java).forEach { tile ->
                    tile.neighbours.addAll(listOf(Vector2i(-1, 0), Vector2i(0, -1), Vector2i(1, 0), Vector2i(0, 1))
                        .map { tile.pos + it }
                        .filter { it.y in direGram.indices && it.x in direGram[it.y].indices }
                        .mapNotNull { direGram[it.y][it.x] }
                        .filterIsInstance(Floor::class.java))
                }
            }
        }

    override fun part1(): Any {

        // ... u wot m8

        return -1
    }

    override fun part2(): Any {

        return -1
    }
}

internal interface Tile {
    fun canMoveTo(): Boolean
}

internal class Wall : Tile {
    override fun canMoveTo() = false
}

internal class Floor(val pos: Vector2i, private var occupant: Amphipod? = null) : Tile {
    val neighbours = mutableListOf<Floor>()
    override fun canMoveTo() = occupant == null
}

internal class Amphipod(val type: AmphipodType)

internal enum class AmphipodType(val type: Char, val cost: Int) {
    Amber('A', 1),
    Bronze('B', 10),
    Copper('C', 100),
    Desert('D', 1000);

    companion object {
        val types = entries.associateBy(AmphipodType::type)
        fun fromType(type: Char) = types[type]!!
    }
}