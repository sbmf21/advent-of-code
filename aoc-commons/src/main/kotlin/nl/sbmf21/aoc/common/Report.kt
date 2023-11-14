package nl.sbmf21.aoc.common

import nl.sbmf21.aoc.common.Format.stringifyNumber
import nl.sbmf21.aoc.common.Format.timeString
import nl.sbmf21.aoc.common.table.Align.CENTER
import nl.sbmf21.aoc.common.table.Align.RIGHT
import nl.sbmf21.aoc.common.table.table
import java.util.Collections.synchronizedList

internal class Report(private val aoc: AocBase) {

    private val timings = synchronizedList(mutableListOf<TimedRunner>())

    fun run(meta: DayMeta<Day>): Day {
        val runner = TimedRunner(meta)
        runner.run()
        timings.add(runner)
        return runner.day
    }

    fun render(threads: String) {
        table {
            row {
                cell { text = "* Advent of Code ${aoc.name} *"; align = CENTER; colspan = 7 }
            }
            ruler()
            row {
                cell { text = "Day"; align = CENTER }
                cell { text = "Answers"; align = CENTER; colspan = 2 }
                cell { text = "Timings"; align = CENTER; colspan = 4 }
            }
            ruler()
            row {
                cell()
                cell { text = "Part 1"; align = CENTER }
                cell { text = "Part 2"; align = CENTER }
                cell { text = "Total"; align = CENTER }
                cell { text = "Setup"; align = CENTER }
                cell { text = "Part 1"; align = CENTER }
                cell { text = "Part 2"; align = CENTER }
            }
            ruler()

            if (timings.isEmpty()) {
                row { cell { colspan = 7 } }
                row {
                    cell { text = "No days have run."; align = CENTER; colspan = 7 }
                }
                row { cell { colspan = 7 } }
            } else timings.sortedBy { it.day.number }.forEach {
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

            ruler()

            row {
                cell { text = "Total time"; align = RIGHT; colspan = 5 }
                cell { text = timeString(timings.sumOf(TimedRunner::totalTime), true); align = RIGHT; colspan = 2 }
            }

            row {
                cell { text = "Thread count"; align = RIGHT; colspan = 5 }
                cell { text = threads; align = RIGHT; colspan = 2 }
            }
        }.apply(::println)
    }
}