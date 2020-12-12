package io.frutsel_.aoc.report

import io.frutsel_.aoc.common.ADay
import kotlin.math.roundToInt

internal class Timing(var day: ADay) {
    internal var totalTime: Long? = null
    internal var part1Time: Long? = null
    internal var part2Time: Long? = null
    internal var part1Value: Number? = null
    internal var part2Value: Number? = null

    fun totalTime(): String = timeString(totalTime!!)
    fun part1Time(): String = timeString(part1Time!!)
    fun part2Time(): String = timeString(part2Time!!)

    private fun timeString(time: Long) = when {
        time > s_ -> "${round(time, s_)}  s"
        time > ms -> "${round(time, ms)} ms"
        time > us -> "${round(time, us)} μs"
        else -> "$time ns"
    }
}

internal const val s_ = 1_000_000_000.0
internal const val ms = 1_000_000.0
internal const val us = 1_000.0

internal fun round(time: Long, value: Double): String = (time / value).roundToInt().toString()
