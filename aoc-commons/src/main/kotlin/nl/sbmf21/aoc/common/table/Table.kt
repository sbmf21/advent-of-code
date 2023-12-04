package nl.sbmf21.aoc.common.table

import nl.sbmf21.aoc.common.Color
import nl.sbmf21.aoc.common.Color.Companion.REGEX
import nl.sbmf21.aoc.common.table.Align.*

internal class Table {

    private val rows = mutableListOf<Line>()
    private var padding: Int = 1
        set(value) {
            if (value < 0) throw Error("Padding cannot be negative")
            field = value
        }

    fun row(configure: Row.() -> Unit) {
        rows += Row().apply(configure)
    }

    fun ruler() {
        rows += Ruler
    }

    override fun toString() = TableRender(this).toString()

    private class TableRender(table: Table) {

        companion object {
            private const val PADDING = ' '
            private const val SEPARATOR = '│'
        }

        private val padding = table.padding
        private val cellStart = SEPARATOR + PADDING.repeat(padding)
        private val cellSeparator = PADDING.repeat(padding) + SEPARATOR + PADDING.repeat(padding)
        private val cellEnd = PADDING.repeat(padding) + SEPARATOR

        private val lines = buildList {
            add(Ruler)
            addAll(table.rows)
            add(Ruler)
        }

        private val widths = buildMap {
            val contentCache = buildMap {
                lines.filterIsInstance<Row>().forEachIndexed { index, row ->
                    this[index] = buildList {
                        row.cells.forEach { cell ->
                            val parsedText = parseAscii(cell.text).split("\n").maxBy(String::length)
                            if (cell.colspan > 1) {
                                val text = parsedText + "#".repeat((parsedText.length % cell.colspan).let {
                                    if (it > 0) cell.colspan - it else 0
                                })

                                val section = (text.length - (cellSeparator.length * (cell.colspan - 1))) / cell.colspan
                                repeat(cell.colspan) {
                                    add(if (section < 0) 0 else section)
                                }
                            } else {
                                add(parseAscii(parsedText).length)
                            }
                        }
                    }
                }
            }

            for (i in 0 until contentCache.maxOf { it.value.size }) {
                this[i] = contentCache.maxOf { (_, values) -> values.getOrNull(i) ?: 0 }
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

        override fun toString(): String {
            val output = buildList {
                lines.forEachIndexed { index, line ->
                    when (line) {
                        is Ruler -> {
                            val width =
                                widths.values.sum() + cellSeparator.length * (widths.size - 1) + (padding * 2)
                            val (first, last) = when (index) {
                                0 -> '┌' to '┐'
                                lines.lastIndex -> '└' to '┘'
                                else -> '├' to '┤'
                            }

                            val borders = mutableMapOf<Int, Char>()
                            mapBorders(index - 1) { borders[it] = '┴' }
                            mapBorders(index + 1) { borders[it] = if (it in borders) '┼' else '┬' }

                            add(borders.toList().fold(first + "─".repeat(width) + last) { current, (index, border) ->
                                current.substring(0, index) + border + current.substring(index + 1, current.length)
                            })
                        }

                        is Row -> {
                            val cells = line.cells
                            val rowCount = cells.maxOf { it.text.split("\n").size }

                            repeat(rowCount) {
                                add(cellStart + line.cells.joinToString(cellSeparator) { cell ->
                                    val textParts = cell.text.split("\n")
                                    val current = textParts.getOrElse(it) { "" }

                                    val previous = if (it > 0) {
                                        textParts.take(it).joinToString("") {
                                            REGEX.findAll(it).joinToString("", transform = MatchResult::value)
                                        }
                                    } else ""

                                    val width = columnCache[cell]!!.sumOf { widths[it]!! } +
                                        cellSeparator.repeat(cell.colspan - 1).length -
                                        parseAscii(current).length
                                    val renderText = previous + current + Color.RESET

                                    when (cell.align) {
                                        CENTER -> {
                                            val left = width / 2
                                            val right = width - left

                                            " ".repeat(left) + renderText + " ".repeat(right)
                                        }

                                        LEFT -> renderText + " ".repeat(width)
                                        RIGHT -> " ".repeat(width) + renderText
                                    }
                                } + cellEnd)
                            }
                        }
                    }
                }
            }

            return output.joinToString("\n")
        }

        private fun mapBorders(lineIndex: Int, apply: (Int) -> Unit) {
            var index = 0
            when (val line = lines.getOrNull(lineIndex)) {
                is Row -> line.cells.take(line.cells.lastIndex).map { columnCache[it]!! }.forEach { columns ->
                    index += columns.map { widths[it]!! }.sumOf { it } + (cellSeparator.length * columns.size - 1) + 1
                    apply(index)
                }

                else -> {}
            }
        }

        private fun parseAscii(input: String) = input.replace(REGEX, "")
    }
}

private fun Char.repeat(n: Int) = "$this".repeat(n)