package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.days.Day23.Tile.Companion.tile
import java.util.*
import kotlin.math.abs

class Day23(input: List<String>) : ADay(input) {

    override fun part1() = calculate(*input.toTypedArray())
    override fun part2() = calculate(*input.aTake(3), "  #D#C#B#A#  ", "  #D#B#A#C#  ", *input.aTakeLast(2))

    private fun calculate(vararg input: String): Int {
        val steps = PriorityQueue<PricedDiagram>()
        val visited = mutableSetOf<Diagram>()
        val costs = mutableMapOf<Diagram, Int>()

        steps.add(PricedDiagram(input.toList().subList(1, input.size - 1).map(String::toList)))

        while (steps.isNotEmpty()) {
            val current = steps.poll()
            visited.add(current.diagram)
            current.diagram.steps().filterNot { visited.contains(it.diagram) }.forEach { (diagram, cost) ->
                val stepCost = current.cost + cost
                if (stepCost < (costs[diagram] ?: Int.MAX_VALUE)) {
                    costs[diagram] = stepCost
                    steps.add(diagram.priced(stepCost))
                }
            }
        }

        return costs.keys.first(Diagram::finished).let(costs::getValue)
    }

    private data class Diagram(private val diagram: List<List<Char>>) {
        private val hallway = diagram[0]
        private val destinations = Amphipod.entries.associate { type ->
            type.char to Room(type, diagram.subList(1, diagram.size).map { tile(it[type.roomIndex]) })
        }

        private val possibleIndices
            get() = hallway.indices
                .filterNot { it in Amphipod.indices }
                .filter { hallway[it] == Empty.char }

        val finished get() = destinations.values.all(Room::valid)

        fun priced(cost: Int) = PricedDiagram(diagram, cost)

        fun steps() = buildList {
            hallway.withIndex().filter { it.value.isLetter() && destinations.getValue(it.value).emptyOrValid }.forEach {
                val room = destinations.getValue(it.value)
                if (isPathClear(it.index, room.index)) {
                    val y = room.content.lastIndexOf(Empty) + 1
                    val cost = (abs(it.index - room.index) + y) * Amphipod.fromType(it.value).cost
                    add(PricedDiagram(diagram.map { row -> row.toMutableList() }.apply {
                        get(0)[it.index] = Empty.char
                        get(y)[room.index] = it.value
                    }, cost))
                }
            }
            destinations.values.filter { it.invalid }.forEach { room ->
                val toMove = room.content.withIndex().first { it.value != Empty }
                possibleIndices.forEach { index ->
                    if (isPathClear(index, room.index)) {
                        val y = toMove.index + 1
                        val cost = (abs(room.index - index) + y) * (toMove.value as Amphipod).cost
                        add(PricedDiagram(diagram.map { row -> row.toMutableList() }.apply {
                            get(y)[room.index] = Empty.char
                            get(0)[index] = toMove.value.char
                        }, cost))
                    }
                }
            }
        }

        private fun isPathClear(start: Int, end: Int) =
            hallway.slice(if (start > end) (start - 1) downTo end else (start + 1)..end).all { it == Empty.char }
    }

    private class PricedDiagram(input: List<List<Char>>, val cost: Int = 0) : Comparable<PricedDiagram> {
        val diagram = Diagram(input)
        override fun compareTo(other: PricedDiagram) = cost.compareTo(other.cost)
        operator fun component1() = diagram
        operator fun component2() = cost
    }

    private class Room(val type: Amphipod, val content: List<Tile>) {
        val index = type.roomIndex
        val valid get() = content.all { it == type }
        val emptyOrValid get() = content.all { it == Empty || it == type }
        val invalid get() = !emptyOrValid
    }

    private interface Tile {
        val char: Char

        companion object {
            fun tile(type: Char) = if (type in Amphipod.types) Amphipod.fromType(type) else Empty
        }
    }

    private enum class Amphipod(override val char: Char, val cost: Int, val roomIndex: Int) : Tile {
        AMBER('A', 1, 3),
        BRONZE('B', 10, 5),
        COPPER('C', 100, 7),
        DESERT('D', 1000, 9);

        companion object {
            val indices = entries.map(Amphipod::roomIndex)
            val types = entries.associateBy(Amphipod::char)
            fun fromType(type: Char) = types[type]!!
        }
    }

    private object Empty : Tile {
        override val char: Char = '.'
    }
}