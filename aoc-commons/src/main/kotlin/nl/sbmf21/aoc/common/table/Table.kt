package nl.sbmf21.aoc.common.table

import nl.sbmf21.aoc.common.table.Align.*

internal class Table {

    private val rows = mutableListOf<Line>()

    fun row(configure: Row.() -> Unit) {
        rows += Row().apply(configure)
    }

    fun ruler() {
        if (rows.last() is Ruler) return
        rows += Ruler
    }

    override fun toString(): String {
        val rows = mutableListOf(*rows.toTypedArray())

        rows.add(0, Ruler)
        rows.add(Ruler)

        val cellCache = buildMap {
            rows.filterIsInstance<Row>().forEachIndexed { index, row ->
                this[index] = buildList {
                    row.cells.forEach { cell ->
                        if (cell.colspan > 1) {
                            val text = cell.text + "#".repeat((cell.text.length % cell.colspan).let {
                                if (it > 0) cell.colspan - it
                                else 0
                            })

                            val section = (text.length - (3 * (cell.colspan - 1))) / cell.colspan
                            repeat(cell.colspan) {
                                add("#".repeat(if (section < 0) 0 else section))
                            }
                        } else add(cell.text)
                    }
                }
            }
        }

        val cols = buildMap {
            for (i in 0 until cellCache.maxOf { it.value.size }) {
                this[i] = cellCache.maxOf { (_, values) ->
                    values.getOrNull(i)?.length ?: 0
                }
            }
        }

        val colCache = buildMap {
            rows.filterIsInstance<Row>().forEach { row ->
                var index = 0
                row.cells.forEach { cell ->
                    this[cell] = buildList {
                        for (i in 0 until cell.colspan) {
                            this += index + i
                        }
                    }

                    index += cell.colspan
                }
            }
        }

        val s = buildString {
            var lastRowIndex = -1

            rows.forEachIndexed { index, row ->
                appendLine(when (row) {
                    is Ruler -> {
                        var ruler = "─".repeat(cols.map { it.value }.sum() + 3 * (cols.size - 1) + 2)

                        val first = when {
                            lastRowIndex + 2 >= rows.size -> '└'
                            lastRowIndex >= 0 -> '├'
                            else -> '┌'
                        }

                        val last = when {
                            lastRowIndex + 2 >= rows.size -> '┘'
                            lastRowIndex >= 0 -> '┤'
                            else -> '┐'
                        }

                        ruler = first + ruler + last

                        if (lastRowIndex >= 0 && lastRowIndex + 2 >= rows.size) {
                            val lr = rows[lastRowIndex] as Row

                            var l = 0

                            // in what columns the cell is
                            lr.cells.take(lr.cells.size - 1).map { colCache[it]!! }
                                .forEach { columns ->
                                    val w = columns.map { cols[it]!! }.sumOf { it } + (3 * columns.size - 1)
                                    ruler = ruler.substring(0, l + w + 1) + '┴' + ruler.substring(
                                        l + w + 2,
                                        ruler.length
                                    )
                                    l += w + 1
                                }
                        } else if (lastRowIndex >= 0) {
                            val lr = rows[lastRowIndex] as Row
                            val nr = rows[lastRowIndex + 2] as Row

                            val c = mutableMapOf<Int, Char>()
                            var l = 0

                            lr.cells.take(lr.cells.size - 1).map { colCache[it]!! }.forEach { columns ->
                                l += columns.map { cols[it]!! }.sumOf { it } + (3 * columns.size - 1) + 1
                                c[l] = '┴'
                            }

                            l = 0

                            nr.cells.take(nr.cells.size - 1).map { colCache[it]!! }.forEach { columns ->
                                l += columns.map { cols[it]!! }.sumOf { it } + (3 * columns.size - 1) + 1
                                c[l] = if (l in c) '┼' else '┬'
                            }

                            c.forEach { (l, w) ->
                                ruler = ruler.substring(0, l) + w + ruler.substring(l + 1, ruler.length)
                            }
                        }

                        ruler
                    }

                    is Row -> "│ " + row.cells.joinToString(" │ ") { cell ->
                        lastRowIndex = index
                        val width =
                            colCache[cell]!!.sumOf { cols[it]!! } + " │ ".repeat(cell.colspan - 1).length - cell.text.length

                        when (cell.align) {
                            CENTER -> {
                                val left = width / 2
                                val right = width - left

                                " ".repeat(left) + cell.text + " ".repeat(right)
                            }

                            LEFT -> cell.text + " ".repeat(width)
                            RIGHT -> " ".repeat(width) + cell.text
                        }
                    } + " │"
                })
            }
        }

        return s.trim()
    }
}