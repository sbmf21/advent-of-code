package nl.sbmf21.aoc22.days.simulations

import nl.sbmf21.aoc.common.*
import nl.sbmf21.aoc22.days.Day5

class Crates(day: Day5) : Simulation<Day5>() {

    override val frames: List<Frame>

    init {
        val part1frames = store(day) { it.part1() }
        val part2frames = store(day) { it.part2() }
        val height = (part1frames + part2frames).maxOf { (_, stacks) -> stacks.maxOf { it.size } }
        frames = List(part1frames.size) { i -> CraneFrame(part1frames[i]!!, part2frames[i]!!, height) }
    }

    private fun store(day: Day5, part: (day: Day5) -> String): MutableMap<Int, List<ArrayDeque<Char>>> {
        val frames = mutableMapOf<Int, List<ArrayDeque<Char>>>()
        day.store = { frame, stacks -> frames[frame] = stacks.map { ArrayDeque(it) } }
        part.invoke(day)
        return frames
    }
}

private class CraneFrame(
    part1stacks: List<ArrayDeque<Char>>,
    part2stacks: List<ArrayDeque<Char>>,
    height: Int,
) : Frame() {

    override val content: String

    init {
        val part1lines = mapStacks(part1stacks, height)
        val part2lines = mapStacks(part2stacks, height)
        val width = part1lines[0].length + 12 + part2lines[0].length
        val lines = mutableListOf<String>()
        lines.addAll(headers(5, 31))
        lines.add(partHeader(1, part1lines) + partHeader(2, part2lines))
        lines.add("#".repeat(width))
        lines.addAll(MutableList(part1lines.size) { "#  " + part1lines[it] + "  ##  " + part2lines[it] + "  #" })
        lines.add("#".repeat(width))
        content = lines.joinToString("\n")
    }

    private fun partHeader(part: Int, lines: List<String>): String {
        val gap = lines[0].length / 2
        return " ".repeat(gap) + "${GREEN}Part $YELLOW$part$RESET" + " ".repeat(gap)
    }

    private fun mapStacks(stacks: List<ArrayDeque<Char>>, height: Int): MutableList<String> {
        val lines = mutableListOf<String>()
        val stackLines = stacks.fold(List(height) { mutableListOf<String>() }) { stackLines, stack ->
            for (y in 0 until height) {
                if (y < stack.size) stackLines[y].add("[${stack[y]}]")
                else stackLines[y].add(" ".repeat(3))
            }
            stackLines
        }.map { it.joinToString(" ") }.reversed()
        lines.addAll(stackLines)
        lines.add(List(stacks.size) { i -> " $i " }.joinToString(" "))
        return lines
    }
}
