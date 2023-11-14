package nl.sbmf21.aoc.common

import kotlin.math.round

internal object Format {
    private const val SECONDS = 1_000_000_000.0
    private const val MILLIS = 1_000_000.0
    private const val MICROS = 1_000.0

    fun timeString(time: Long?) = when {
        time == null -> "NaN   "
        time > SECONDS -> "${format(time, SECONDS)}  s"
        time > MILLIS -> "${format(time, MILLIS)} ms"
        time > MICROS -> "${format(time, MICROS)} μs"
        else -> "$time ns"
    }

    fun stringifyNumber(n: Any?) = when (n) {
        null -> "none"
        is Long -> n.toBigInteger().toString()
        is Double -> n.toBigDecimal().toString()
        else -> n.toString()
    }

    private fun format(time: Long, value: Double): String = (round((time / value) * 100) / 100).toString()
}