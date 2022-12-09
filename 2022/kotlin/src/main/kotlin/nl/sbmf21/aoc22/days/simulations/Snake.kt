package nl.sbmf21.aoc22.days.simulations

import nl.sbmf21.aoc22.days.Day9
import nl.sbmf21.math.Vector2i

class Snake(day: Day9) {

    private val frames: List<Frame>

    init {
        val grid = mutableMapOf<Int, MutableMap<Vector2i, Int>>()
        day.store = { frame, index, pos ->
            val gridFrame = grid.getOrPut(frame) { mutableMapOf() }
            if (pos !in gridFrame) gridFrame[pos] = index
        }
        day.run()
        frames = grid.map { (id, content) -> Frame(id + 1, grid.size, content) }
    }

    fun runSnake() = frames.forEach {
        clear()
        it.print()
        sleep()
    }

    private fun clear() = println("\u001b\u0063")
    private fun sleep() = Thread.sleep(1000 / 60) // 60 fps
}

// ASCI colors https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html

private const val GREEN = "\u001b[32m"
private const val YELLOW = "\u001b[33m"
private const val GRAY = "\u001B[38;5;245m"
private const val RESET = "\u001b[0m"

private val center = Vector2i()
private const val OFFSET_X = 8
private const val OFFSET_Y = 5

private val programHeaders = listOf(
        "$YELLOW     \u066D  ${GREEN}Advent of Code  $YELLOW\u066D$RESET",
        "          ${YELLOW}2022 day 9$RESET\n",
)

private class Frame(frame: Int, maxFrames: Int, content: Map<Vector2i, Int>) {

    private val content: String

    init {
        val centerX = (content.keys.maxOf { it.x } + content.keys.minOf { it.x }) / 2
        val centerY = (content.keys.maxOf { it.y } + content.keys.minOf { it.y }) / 2
        val xRange = (centerX - OFFSET_X)..(centerX + OFFSET_X + 1)
        val yRange = (centerY + OFFSET_Y) downTo (centerY - OFFSET_Y)

        val lines = mutableListOf<String>()
        lines.addAll(programHeaders)

        for (y in yRange) {
            var line = "$GRAY${" ".repeat(5 - y.toString().length)}$y$RESET "
            for (x in xRange) {
                val point = Vector2i(x, y)
                line += when (point) {
                    in content -> if (content[point] == 0) "H" else content[point]
                    center -> "s"
                    else -> "."
                }
            }
            lines.add(line)
        }

        val xAxis = MutableList(5) { " ".repeat(6) + GRAY }
        for (x in xRange) {
            val xContent = "${" ".repeat(4 - x.toString().length)}$x ".replace('-', '\u02c8')
            for (i in xContent.indices) xAxis[4 - i] = xAxis[4 - i] + xContent[i]
        }
        xAxis.forEachIndexed { i, s -> xAxis[i] = s + RESET }
        lines.addAll(xAxis)
        lines.add("\n      ${GRAY}Frame $frame/$maxFrames${RESET}")

        this.content = lines.joinToString("\n")
    }

    fun print() = println(content)
}
