package nl.sbmf21.aoc.common

import nl.sbmf21.aoc.common.Format.stringify
import nl.sbmf21.aoc.common.Format.timeString
import nl.sbmf21.aoc.common.table.Align.LEFT
import nl.sbmf21.aoc.common.table.Align.RIGHT
import nl.sbmf21.aoc.common.table.Row
import kotlin.properties.Delegates
import kotlin.system.measureNanoTime

internal data class TimedRunner<T : Puzzle>(private val meta: PuzzleMeta<T>) {

    internal var totalTime by Delegates.notNull<Long>()
        private set

    internal var setupTime by Delegates.notNull<Long>()
        private set

    internal var part1Time by Delegates.notNull<Long>()
        private set

    internal var part2Time by Delegates.notNull<Long>()
        private set

    internal var solutionTime by Delegates.notNull<Long>()
        private set

    internal lateinit var puzzle: T
        private set

    internal lateinit var part1Value: Any
        private set

    internal lateinit var part2Value: Any
        private set
    internal lateinit var solutionValue: Any
        private set

    internal fun run() {
        totalTime = measureNanoTime {
            setupTime = measureNanoTime { puzzle = meta.build() }

            when (val puzzle = puzzle) {
                is Day -> {
                    part1Time = measureNanoTime { part1Value = execute(puzzle::part1) }
                    part2Time = measureNanoTime { part2Value = execute(puzzle::part2) }
                }

                is FinalDay -> {
                    solutionTime = measureNanoTime { solutionValue = execute(puzzle::solution) }
                }
            }
        }
    }

    private fun execute(block: () -> Any) = try {
        block()
    } catch (t: Throwable) {
        t
    }

    companion object {
        fun Row.report(runner: TimedRunner<*>, index: Int) {
            val puzzle = runner.puzzle

            cell { text = "${Color.BLUE}${Color.BOLD}${puzzle.number}"; align = RIGHT }
            cell { text = "${Color.BLUE}${Color.BOLD}" + stringify(index); align = RIGHT }

            when (puzzle) {
                is Day -> {
                    cell {
                        text = stringify(runner.part1Value)
                        align = alignValue(runner.part1Value)
                    }

                    cell {
                        text = stringify(runner.part2Value)
                        align = alignValue(runner.part2Value)
                    }
                }

                is FinalDay -> {
                    cell {
                        text = stringify(runner.solutionValue)
                        align = alignValue(runner.solutionValue)
                        colspan = 2
                    }
                }
            }

            cell { text = timeString(runner.totalTime); align = RIGHT }
            cell { text = timeString(runner.setupTime); align = RIGHT }

            when (puzzle) {
                is Day -> {
                    cell { text = timeString(runner.part1Time); align = RIGHT }
                    cell { text = timeString(runner.part2Time); align = RIGHT }
                }

                is FinalDay -> {
                    cell { text = timeString(runner.solutionTime); align = RIGHT; colspan = 2 }
                }
            }
        }

        private fun alignValue(value: Any) = when {
            value is Throwable -> LEFT
            else -> RIGHT
        }
    }
}