package nl.sbmf21.aoc.common.report

internal class Row {

    private val _cells = mutableListOf<Cell>()
    val cells get() = _cells.toList()

    fun cell(configure: Cell.() -> Unit = {}) {
        _cells += Cell().apply(configure)
    }
}