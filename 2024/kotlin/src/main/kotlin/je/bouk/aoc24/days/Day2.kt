package je.bouk.aoc24.days

import je.bouk.aoc24.days.day2.Compare
import je.bouk.aoc24.days.day2.Report
import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.splitToInts
import nl.sbmf21.aoc.common.subList
import kotlin.math.abs

class Day2 : Day() {

    private val reports = input.map { it.splitToInts(" ") }

    override fun part1() = reports.count(::isSave)

    override fun part2() = reports.count { report ->
        for (test in testIndices(report)) {
            if (isSave(report.filterIndexed { index, _ -> index != test })) {
                return@count true
            }
        }
        false
    }

    private fun testIndices(report: Report): Set<Int> = buildSet {
        this += 0
        this += report.lastIndex

        for (i in 1..<report.lastIndex) {
            val diff = abs(report[i - 1] - report[i + 1])
            if (diff > 0 && diff < 3) this += i
        }
    }

    private fun isSave(report: Report): Boolean {
        return check(report) { left, right -> left - right }
            || check(report) { left, right -> right - left }
    }

    private fun check(report: Report, diff: Compare) = report.subList(1).withIndex().all { (index, current) ->
        val diff = diff(current, report[index])
        diff > 0 && diff <= 3
    }
}