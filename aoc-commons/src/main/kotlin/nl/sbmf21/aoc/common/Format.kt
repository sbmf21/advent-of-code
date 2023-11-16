package nl.sbmf21.aoc.common

import nl.sbmf21.aoc.common.Color.*

internal object Format {
    private const val SECONDS = 1_000_000_000.0
    private const val MILLIS = 1_000_000.0
    private const val MICROS = 1_000.0

    private const val SECONDS_SUFFIX = " s"
    private const val MILLIS_SUFFIX = "ms"
    private const val MICROS_SUFFIX = "μs"

    fun timeString(time: Long, trimSuffix: Boolean = false, color: Color = RESET) = when {
        time >= SECONDS -> {
            val seconds = time / SECONDS
            val actualColor = when {
                seconds > 5 -> RED
                else -> YELLOW
            }

            actualColor + format(time, SECONDS, SECONDS_SUFFIX, trimSuffix)
        }

        time >= MILLIS -> {
            val seconds = time / MILLIS
            val actualColor = when {
                seconds > 500 -> YELLOW
                else -> color
            }

            actualColor + format(time, MILLIS, MILLIS_SUFFIX, trimSuffix)
        }

        time >= MICROS -> color + format(time, MICROS, MICROS_SUFFIX, trimSuffix)
        else -> "$color$time ns"
    }

    fun stringifyNumber(n: Any) = when (n) {
        is Long -> n.toBigInteger().toString()
        is Double -> n.toBigDecimal().toString()
        else -> n.toString()
    }

    private fun format(time: Long, value: Double, suffix: String, trimSuffix: Boolean) =
        "%,.2f".format(time / value) + "$RESET ${if (trimSuffix) suffix.trim() else suffix}"
}