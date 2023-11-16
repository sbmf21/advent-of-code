package nl.sbmf21.aoc.common.table

import nl.sbmf21.aoc.common.Color
import nl.sbmf21.aoc.common.table.Align.*

internal class Table {

    private val rows = mutableListOf<Line>()

    fun row(configure: Row.() -> Unit) {
        rows += Row().apply(configure)
    }

    fun ruler() {
        rows += Ruler
    }

    override fun toString() = TableRender().toString()

    private inner class TableRender {
        private val lines = buildList {
            add(0, Ruler)
            addAll(this@Table.rows)
            add(Ruler)
        }

        private val widths = buildMap {
            val contentCache = buildMap {
                lines.filterIsInstance<Row>().forEachIndexed { index, row ->
                    this[index] = buildList {
                        row.cells.forEach { cell ->
                            val parsedText = parseAscii(cell.text)
                            if (cell.colspan > 1) {
                                val text = parsedText + "#".repeat((parsedText.length % cell.colspan).let {
                                    if (it > 0) cell.colspan - it
                                    else 0
                                })

                                val section = (text.length - (3 * (cell.colspan - 1))) / cell.colspan
                                repeat(cell.colspan) {
                                    add("#".repeat(if (section < 0) 0 else section))
                                }
                            } else add(parsedText)
                        }
                    }
                }
            }

            for (i in 0 until contentCache.maxOf { it.value.size }) {
                this[i] = contentCache.maxOf { (_, values) -> values.getOrNull(i)?.length ?: 0 }
            }
        }
        private val columnCache = buildMap {
            lines.filterIsInstance<Row>().forEach { row ->
                row.cells.fold(0) { index, cell ->
                    this[cell] = buildList { for (i in 0 until cell.colspan) this += index + i }
                    index + cell.colspan
                }
            }
        }

        override fun toString() = lines.withIndex().joinToString("\n") { (index, line) ->
            when (line) {
                is Ruler -> {
                    val width = widths.values.sum() + 3 * (widths.size - 1) + 2
                    val (first, last) = when (index) {
                        0 -> '┌' to '┐'
                        lines.lastIndex -> '└' to '┘'
                        else -> '├' to '┤'
                    }

                    val borders = mutableMapOf<Int, Char>()
                    mapBorders(index - 1) { borders[it] = '┴' }
                    mapBorders(index + 1) { borders[it] = if (it in borders) '┼' else '┬' }

                    borders.toList().fold(first + "─".repeat(width) + last) { current, (index, border) ->
                        current.substring(0, index) + border + current.substring(index + 1, current.length)
                    }
                }

                is Row -> "│ " + line.cells.joinToString(" │ ") { cell ->
                    val width = columnCache[cell]!!.sumOf { widths[it]!! } +
                        " │ ".repeat(cell.colspan - 1).length -
                        parseAscii(cell.text).length
                    val renderText = cell.text + Color.RESET

                    when (cell.align) {
                        CENTER -> {
                            val left = width / 2
                            val right = width - left

                            " ".repeat(left) + renderText + " ".repeat(right)
                        }

                        LEFT -> renderText + " ".repeat(width)
                        RIGHT -> " ".repeat(width) + renderText
                    }
                } + " │"
            }
        }

        private fun mapBorders(lineIndex: Int, apply: (Int) -> Unit) {
            var index = 0
            when (val lr = lines.getOrNull(lineIndex)) {
                is Row -> lr.cells.take(lr.cells.lastIndex).map { columnCache[it]!! }.forEach { columns ->
                    index += columns.map { widths[it]!! }.sumOf { it } + (3 * columns.size - 1) + 1
                    apply(index)
                }

                else -> {}
            }
        }

        private fun parseAscii(input: String) = input.replace(Color.REGEX, "")
    }
}