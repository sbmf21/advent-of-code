package nl.sbmf21.aoc.common

import nl.sbmf21.aoc.common.Format.stringifyNumber
import nl.sbmf21.aoc.common.Format.timeString
import nl.sbmf21.aoc.common.report.Align.CENTER
import nl.sbmf21.aoc.common.report.Align.RIGHT
import nl.sbmf21.aoc.common.report.table
import java.util.Collections.synchronizedList

internal class Report(private val aoc: AocBase) {

    private val timings = synchronizedList(mutableListOf<TimedRunner>())

    fun run(meta: DayMeta<Day>): Day {
        val runner = TimedRunner(meta)
        runner.run()
        timings.add(runner)
        return runner.day
    }

    fun render() {

        val table2 = table {
            headers {
                cell { text = "* Advent of Code ${aoc.name} *"; align = CENTER; colspan = 7 }
            }
            headers {
                cell()
                cell { text = "Answers"; align = CENTER; colspan = 2 }
                cell { text = "Timings"; align = CENTER; colspan = 4 }
            }
            headers {
                cell { text = "Day"; align = CENTER }
                cell { text = "Part 1"; align = CENTER }
                cell { text = "Part 2"; align = CENTER }
                cell { text = "Total"; align = CENTER }
                cell { text = "Setup"; align = CENTER }
                cell { text = "Part 1"; align = CENTER }
                cell { text = "Part 2"; align = CENTER }
            }

            if (timings.isEmpty()) row {
                cell { text = "No days have run."; colspan = 7 }
            }
            else timings.sortedBy { it.day.number }.forEach {
                row {
                    cell { text = "${it.day.number}"; align = RIGHT }
                    cell { text = stringifyNumber(it.part1Value); align = RIGHT }
                    cell { text = stringifyNumber(it.part2Value); align = RIGHT }
                    cell { text = timeString(it.totalTime); align = RIGHT }
                    cell { text = timeString(it.setupTime); align = RIGHT }
                    cell { text = timeString(it.part1Time); align = RIGHT }
                    cell { text = timeString(it.part2Time); align = RIGHT }
                }
            }

            aggregate {
                cell {
                    text = "Total: " + timeString(timings.sumOf(TimedRunner::totalTime))
                    align = RIGHT
                    colspan = 7
                }
            }
        }

        println(table2)
    }
}