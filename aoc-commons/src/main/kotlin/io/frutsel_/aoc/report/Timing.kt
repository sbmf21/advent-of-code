package io.frutsel_.aoc.report

import io.frutsel_.aoc.common.ADay

internal class Timing(var day: ADay) {
    internal var totalTime: Long? = null
    internal var part1Time: Long? = null
    internal var part2Time: Long? = null
    internal var part1Value: Number? = null
    internal var part2Value: Number? = null

    fun totalTime(): String = timeString(totalTime)
    fun part1Time(): String = timeString(part1Time)
    fun part2Time(): String = timeString(part2Time)

    private fun timeString(time: Long?): String = if (time!! > 1000) {
        (time / 1000.0).toString() + "  s"
    } else {
        "$time ms"
    }
}
