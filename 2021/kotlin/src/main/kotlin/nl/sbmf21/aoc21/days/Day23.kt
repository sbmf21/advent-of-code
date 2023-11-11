package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.days.Day23.AmphiAction.IN
import nl.sbmf21.aoc21.days.Day23.AmphiAction.OUT
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
            current.diagram.steps().filterNot { visited.contains(it.diagram) }.forEach {
                val stepCost = current.cost + it.cost
                if (stepCost < (costs[it.diagram] ?: Int.MAX_VALUE)) {
                    costs[it.diagram] = stepCost
                    steps.add(it.diagram.priced(stepCost))
                }
            }
        }

        return costs.keys.first(Diagram::finished).let(costs::getValue)
    }

    private data class Diagram(private val diagram: List<List<Char>>) {
        private val hallway = diagram[0]
        private val destinations = Amphipod.entries.associate { type -> type.char to Room(type, diagram) }

        private val possibleIndices
            get() = hallway.indices
                .filterNot { it in Amphipod.indices }
                .filter { hallway[it] == Empty.char }

        val finished get() = destinations.values.all(Room::valid)

        fun priced(cost: Int) = PricedDiagram(diagram, cost)

        fun steps() = buildList {
            hallway.withIndex().filter { it.value.isLetter() && destinations.getValue(it.value).emptyOrValid }.forEach {
                val room = destinations.getValue(it.value)
                move(it.index, room.index, OUT, { room.content.lastIndexOf(Empty) }, { Amphipod(it.value) }, ::add)
            }
            destinations.values.filter(Room::invalid).forEach { room ->
                val amphiTile = room.content.withIndex().first { it.value != Empty }
                possibleIndices.forEach {
                    move(room.index, it, IN, { amphiTile.index }, { amphiTile.value as Amphipod }, ::add)
                }
            }
        }

        private fun move(
            start: Int,
            end: Int,
            action: AmphiAction,
            y: () -> Int,
            amphi: () -> Amphipod,
            add: (PricedDiagram) -> Unit,
        ) {
            if (!isPathClear(start, end)) return

            val row = y() + 1
            val amphipod = amphi()

            add(PricedDiagram(diagram.map { it.toMutableList() }.apply {
                // @formatter:off
                this[when (action) { IN -> row; OUT -> 0 }][start] = Empty.char
                this[when (action) { IN -> 0; OUT -> row }][end] = amphipod.char
                // @formatter:on
            }, (abs(start - end) + row) * amphipod.cost))
        }

        private fun isPathClear(start: Int, end: Int) =
            hallway.slice(if (start > end) (start - 1) downTo end else (start + 1)..end).all { it == Empty.char }
    }

    private class PricedDiagram(input: List<List<Char>>, val cost: Int = 0) : Comparable<PricedDiagram> {
        val diagram = Diagram(input)
        override fun compareTo(other: PricedDiagram) = cost.compareTo(other.cost)
    }

    private class Room(private val type: Amphipod, diagram: List<List<Char>>) {
        val index = type.roomIndex
        val content: List<Tile> = diagram.subList(1, diagram.size).map { Tile(it[type.roomIndex]) }
        val valid get() = content.all { it == type }
        val emptyOrValid get() = content.all { it == Empty || it == type }
        val invalid get() = !emptyOrValid
    }

    private interface Tile {
        val char: Char

        companion object {
            operator fun invoke(type: Char) = if (type in Amphipod.types) Amphipod(type) else Empty
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
            operator fun invoke(type: Char) = types[type]!!
        }
    }

    private object Empty : Tile {
        override val char: Char = '.'
    }

    private enum class AmphiAction { IN, OUT }
}