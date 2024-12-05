package nl.sbmf21.aoc.common

import nl.sbmf21.aoc.common.Color.*
import nl.sbmf21.aoc.common.Format.timeString
import nl.sbmf21.aoc.common.TimedRunner.Companion.report
import nl.sbmf21.aoc.common.table.Align.*
import nl.sbmf21.aoc.common.table.table
import java.util.Collections.synchronizedList

internal class Report(private val aoc: AocBase) {

    private val timings = synchronizedList(mutableListOf<TimedRunner<*>>())

    fun run(meta: PuzzleMeta<Puzzle>): Puzzle {
        val runner = TimedRunner(meta)
        runner.run()
        timings.add(runner)
        return runner.puzzle
    }

    fun render(executionTime: Long, threads: String) {
        val totalTime = timings.sumOf(TimedRunner<*>::totalTime)

        table {
            row {
                cell {
                    text = listOf(
                        "${LIGHT_GREEN}Advent",
                        "$STAR     of     $STAR",
                        "Code",
                        "",
                        "${YELLOW}${BOLD}${aoc.name}",
                    ).joinToString("\n")
                    align = CENTER
                    colspan = 8
                }
            }
            ruler()

            if (timings.isEmpty()) {
                row {
                    cell { text = "\nNo days have run.\n"; align = CENTER; colspan = 8 }
                }
            } else {
                row {
                    cell { text = "${GREEN}${BOLD}Execution"; align = CENTER; colspan = 2 }
                    cell { text = "${GREEN}${BOLD}Answers"; align = CENTER; colspan = 2 }
                    cell { text = "${GREEN}${BOLD}Timings"; align = CENTER; colspan = 4 }
                }
                ruler()
                row {
                    cell { text = "${GREEN}${BOLD}Day"; align = CENTER }
                    cell { text = "${GREEN}${BOLD}Order"; align = CENTER }
                    cell { text = "${GREEN}${BOLD}Part 1"; align = CENTER }
                    cell { text = "${GREEN}${BOLD}Part 2"; align = CENTER }
                    cell { text = "${GREEN}${BOLD}Total"; align = CENTER }
                    cell { text = "${GREEN}${BOLD}Setup"; align = CENTER }
                    cell { text = "${GREEN}${BOLD}Part 1"; align = CENTER }
                    cell { text = "${GREEN}${BOLD}Part 2"; align = CENTER }
                }
                ruler()

                timings.sortedBy { it.puzzle.number }.forEach {
                    row {
                        report(it, timings.indexOf(it) + 1)
                    }
                }

                ruler()

                row {
                    cell { text = "Total time"; align = RIGHT; colspan = 6 }
                    cell { text = timeString(totalTime, true, false); align = LEFT; colspan = 2 }
                }

                row {
                    cell { text = "Execution time"; align = RIGHT; colspan = 6 }
                    cell { text = timeString(executionTime, true, false); align = LEFT; colspan = 2 }
                }

                row {
                    cell { text = "Thread count"; align = RIGHT; colspan = 6 }
                    cell { text = threads; align = LEFT; colspan = 2 }
                }
            }
        }.apply(::println)
    }

    private companion object {
        val STAR = "$YELLOW*$LIGHT_GREEN"
    }
}