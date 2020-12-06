package io.frutsel_.aoc.report

internal class Sizes(private var columns: List<Column>) {

    var totalWidth = totalWidth()
    var lineSizes = lineSizes()

    private fun totalWidth(): Int = columns.sumBy { it.len } + columns.size * 3 - 1

    private fun lineSizes(): List<Int> = columns.map { it.len }
}
