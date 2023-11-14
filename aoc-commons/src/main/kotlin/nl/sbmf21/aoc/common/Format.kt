package nl.sbmf21.aoc.common

import kotlin.math.round

internal object Format {
    private const val SECONDS = 1_000_000_000.0
    private const val MILLIS = 1_000_000.0
    private const val MICROS = 1_000.0

    private const val SECONDS_SUFFIX = " s"
    private const val MILLIS_SUFFIX = "ms"
    private const val MICROS_SUFFIX = "μs"

    fun timeString(time: Long, trimSuffix: Boolean = false) = when {
        time > SECONDS -> format(time, SECONDS, SECONDS_SUFFIX, trimSuffix)
        time > MILLIS -> format(time, MILLIS, MILLIS_SUFFIX, trimSuffix)
        time > MICROS -> format(time, MICROS, MICROS_SUFFIX, trimSuffix)
        else -> "$time ns"
    }

    fun stringifyNumber(n: Any) = when (n) {
        is Long -> n.toBigInteger().toString()
        is Double -> n.toBigDecimal().toString()
        else -> n.toString()
    }

    private fun format(time: Long, value: Double, suffix: String, trimSuffix: Boolean) =
        "${round((time / value) * 100) / 100} ${if (trimSuffix) suffix.trim() else suffix}"
}