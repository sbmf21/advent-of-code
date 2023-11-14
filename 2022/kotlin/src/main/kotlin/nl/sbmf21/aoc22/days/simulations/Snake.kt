package nl.sbmf21.aoc22.days.simulations

import nl.sbmf21.aoc.common.*
import nl.sbmf21.aoc22.days.Day9
import nl.sbmf21.math.Vector2i

class Snake(day: Day9) : Simulation<Day9>() {

    override val frames: List<Frame>

    init {
        val grid = mutableMapOf<Int, MutableMap<Vector2i, Int>>()
        day.store = { frame, index, pos ->
            val gridFrame = grid.getOrPut(frame) { mutableMapOf() }
            if (pos !in gridFrame) gridFrame[pos] = index
        }
        day.run()
        frames = grid.map { (id, content) -> SnakeFrame(id + 1, grid.size, content) }
    }

    private companion object {
        private val center = Vector2i()
        private const val OFFSET_X = 8
        private const val OFFSET_Y = 5
    }

    private class SnakeFrame(frame: Int, maxFrames: Int, content: Map<Vector2i, Int>) : Frame() {

        override val content: String

        init {
            val centerX = (content.keys.maxOf { it.x } + content.keys.minOf { it.x }) / 2
            val centerY = (content.keys.maxOf { it.y } + content.keys.minOf { it.y }) / 2
            val xRange = (centerX - OFFSET_X)..(centerX + OFFSET_X + 1)
            val yRange = (centerY + OFFSET_Y) downTo (centerY - OFFSET_Y)

            val lines = mutableListOf<String>()
            lines.addAll(headers(9, 5))

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
    }
}