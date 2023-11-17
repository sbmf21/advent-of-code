package nl.sbmf21.aoc.common

import nl.sbmf21.aoc.common.Format.stringifyNumber
import nl.sbmf21.aoc.common.Format.timeString
import nl.sbmf21.aoc.common.table.Align
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
                    part1Time = measureNanoTime { part1Value = puzzle.part1() }
                    part2Time = measureNanoTime { part2Value = puzzle.part2() }
                }

                is FinalDay -> {
                    solutionTime = measureNanoTime { solutionValue = puzzle.solution() }
                }
            }
        }
    }

    companion object {
        fun Row.report(runner: TimedRunner<*>, index: Int) {
            val puzzle = runner.puzzle

            cell { text = "${Color.BLUE}${Color.BOLD}${puzzle.number}"; align = Align.RIGHT }
            cell { text = "${Color.BLUE}${Color.BOLD}" + stringifyNumber(index); align = Align.RIGHT }

            when (puzzle) {
                is Day -> {
                    cell { text = stringifyNumber(runner.part1Value); align = Align.RIGHT }
                    cell { text = stringifyNumber(runner.part2Value); align = Align.RIGHT }
                }

                is FinalDay -> {
                    cell { text = stringifyNumber(runner.solutionValue); align = Align.RIGHT; colspan = 2 }
                }
            }

            cell { text = timeString(runner.totalTime); align = Align.RIGHT }
            cell { text = timeString(runner.setupTime); align = Align.RIGHT }

            when (puzzle) {
                is Day -> {
                    cell { text = timeString(runner.part1Time); align = Align.RIGHT }
                    cell { text = timeString(runner.part2Time); align = Align.RIGHT }
                }

                is FinalDay -> {
                    cell { text = timeString(runner.solutionTime); align = Align.RIGHT; colspan = 2 }
                }
            }
        }
    }
}