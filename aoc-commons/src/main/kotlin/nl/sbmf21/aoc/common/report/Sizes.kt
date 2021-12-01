package nl.sbmf21.aoc.common.report

internal class Sizes(private var columns: List<Column>) {

    var totalWidth = totalWidth()
    var lineSizes = lineSizes()

    private fun totalWidth(): Int = columns.sumOf { it.len } + columns.size * 3 - 1

    private fun lineSizes(): List<Int> = columns.map { it.len }
}
