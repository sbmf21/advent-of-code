package nl.sbmf21.aoc.common

import kotlin.properties.Delegates
import kotlin.system.measureNanoTime

internal class TimedRunner(private val meta: DayMeta<Day>) {

    internal var totalTime by Delegates.notNull<Long>()
        private set

    internal var setupTime by Delegates.notNull<Long>()
        private set

    internal var part1Time by Delegates.notNull<Long>()
        private set

    internal var part2Time by Delegates.notNull<Long>()
        private set

    internal lateinit var day: Day
        private set

    internal lateinit var part1Value: Any
        private set

    internal lateinit var part2Value: Any
        private set

    internal fun run() {
        totalTime = measureNanoTime {
            setupTime = measureNanoTime { day = meta.build() }
            part1Time = measureNanoTime { part1Value = day.part1() }
            part2Time = measureNanoTime { part2Value = day.part2() }
        }
    }
}