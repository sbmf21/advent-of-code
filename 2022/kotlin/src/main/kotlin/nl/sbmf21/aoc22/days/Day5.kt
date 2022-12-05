package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.mapToInts

private typealias Stack = ArrayDeque<Char>

class Day5(input: List<String>) : ADay(input) {

    private val pattern = Regex("move (\\d+) from (\\d+) to (\\d+)")
    private val stackData = input.subList(0, input.indexOf("")).reversed()
    private val actions = input.subList(input.indexOf("") + 1, input.size).map {
        pattern.find(it)!!.groupValues.subList(1, 4).mapToInts()
    }

    override fun part1() = move { _, to -> to.size }

    override fun part2() = move { i, to -> to.size - i }

    private fun move(index: (i: Int, s: Stack) -> Int): String {
        val stacks = getStacks()
        actions.forEach {
            val from = stacks[it[1] - 1]
            val to = stacks[it[2] - 1]
            for (i in 0 until it[0]) to.add(index(i, to), from.removeLast())
        }
        return stacks.map { it.last() }.joinToString("")
    }

    private fun getStacks(): MutableList<Stack> {
        val stacks = MutableList(stackData[0].split(" ").last().toInt()) { Stack() }
        stackData.subList(1, stackData.size).forEach {
            for (id in 0 until stacks.size) {
                val i = 1 + id * 4
                if (i >= it.length) continue
                val c = it[i]
                if (c == ' ') continue
                stacks[id].add(c)
            }
        }
        return stacks
    }
}
