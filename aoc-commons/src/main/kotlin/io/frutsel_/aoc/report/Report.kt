package io.frutsel_.aoc.report

import io.frutsel_.aoc.common.ADay
import io.frutsel_.aoc.common.AocBase
import kotlin.system.measureNanoTime

internal class Report(private val aoc: AocBase) {

    private val builder = StringBuilder()
    private val timings = mutableListOf<Timing>()
    private val columns = listOf(
        Column("Day") { it.day.number().toString() },
        // Timings
        Column("Total") { it.totalTime() },
        Column("Part 1") { it.part1Time() },
        Column("Part 2") { it.part2Time() },
        // Values
        Column("Part 1") { it.part1Value.toString() },
        Column("Part 2") { it.part2Value.toString() }
    )

    fun time(day: ADay) {
        val timing = Timing(day)

        timing.totalTime = measureNanoTime {
            timing.part1Time = measureNanoTime {
                timing.part1Value = day.part1()
            }

            timing.part2Time = measureNanoTime {
                timing.part2Value = day.part2()
            }
        }

        timings.add(timing)
    }

    fun render() {
        val sizes = calculateSizes()

        addTitle(sizes)
        addHeaders()
        addColumnHeaders(sizes)

        if (timings.size > 0) {
            timings.forEach { addTiming(it) }
            addLine(sizes.lineSizes, '┴', '└', '┘')
        } else addNone(sizes)

        print(builder)
        builder.clear()
    }

    private fun addTitle(sizes: Sizes) {
        val title = listOf(cell("* Advent of Code ${aoc.name} *", sizes.totalWidth - 2, Align.CENTER))
        addLine(title.map { it.length }, '┬', '┌', '┐')
        add(title)
    }

    private fun addNone(sizes: Sizes) {
        val title = listOf(cell("No days have ran.", sizes.totalWidth - 2, Align.CENTER))
        add(title)
        addLine(title.map { it.length }, '┴', '└', '┘')
    }

    private fun addHeaders() {
        val l = listOf(
            cell("Day", columns[0].len, Align.CENTER),
            cell("Timings", columns.subList(1, 4).map { it.len }.sum() + 6, Align.CENTER),
            cell("Answers", columns.subList(4, 6).map { it.len }.sum() + 3, Align.CENTER)
        )

        addLine(l.map { it.length }, '┬')
        add(l)
    }

    private fun addColumnHeaders(sizes: Sizes) {
        addLine(sizes.lineSizes)
        add(columns.map { cell(it.header, it.len, Align.CENTER) })
        addLine(sizes.lineSizes)
    }

    private fun addLine(sizes: List<Int>, sep: Char = '┼', left: Char = '├', right: Char = '┤') {
        builder
            .append(join(sizes.map { "─".repeat(it) }, '─', sep, left, right))
            .appendLine()
    }

    private fun addTiming(timing: Timing) {
        add(columns.map { cell(it.value(timing), it.len, Align.RIGHT) })
    }

    private fun add(line: List<String>) {
        builder.append(join(line)).appendLine()
    }

    private fun cell(value: String, width: Int, align: Align): String = align.render(value, width)

    private fun join(
        line: List<String>,
        spacer: Char = ' ',
        sep: Char = '│',
        left: Char = sep,
        right: Char = sep
    ): String = listOf(
        "$left$spacer",
        line.joinToString(separator = "$spacer$sep$spacer"),
        "$spacer$right"
    ).joinToString(separator = "")

    private fun calculateSizes(): Sizes {
        columns.forEach { it.calculate(timings) }

        return Sizes(columns)
    }
}