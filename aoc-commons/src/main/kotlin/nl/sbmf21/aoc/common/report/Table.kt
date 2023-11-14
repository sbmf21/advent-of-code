package nl.sbmf21.aoc.common.report

import de.vandermeer.asciitable.AsciiTable
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment

internal class Table {

    private val headers = mutableListOf<Row>()
    private val rows = mutableListOf<Row>()
    private val aggregates = mutableListOf<Row>()

    fun headers(configure: Row.() -> Unit) {
        headers += Row().apply(configure)
    }

    fun row(configure: Row.() -> Unit) {
        rows += Row().apply(configure)
    }

    fun aggregate(configure: Row.() -> Unit) {
        aggregates += Row().apply(configure)
    }

    override fun toString(): String {
        val ascii = AsciiTable()
        ascii.context.width = 160

        ascii.addRule()
        headers.forEach { ascii.finalizeRow(it); ascii.addRule() }
        rows.forEach { ascii.finalizeRow(it) }
        aggregates.forEach { ascii.addRule(); ascii.finalizeRow(it) }
        ascii.addRule()

        return ascii.render()
    }

    private fun AsciiTable.finalizeRow(row: Row) {
        val atRow = addRow(buildList {
            row.cells.forEach {
                for (i in 0 until it.colspan - 1) add(null)
                add(it.text)
            }
        })

        var index = 0

        row.cells.forEach {
            index += it.colspan - 1

            atRow.cells[index].context.paddingLeft = 1
            atRow.cells[index].context.paddingRight = 1

            atRow.cells[index].context.textAlignment = when (it.align) {
                Align.CENTER -> TextAlignment.CENTER
                Align.LEFT -> TextAlignment.LEFT
                Align.RIGHT -> TextAlignment.RIGHT
            }

            index++
        }
    }
}