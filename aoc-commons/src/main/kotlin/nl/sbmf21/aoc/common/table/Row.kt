package nl.sbmf21.aoc.common.table

internal class Row : Line {

    private val _cells = mutableListOf<Cell>()
    val cells get() = _cells.toList()

    fun cell(configure: Cell.() -> Unit = {}) {
        _cells += Cell().apply(configure)
    }
}