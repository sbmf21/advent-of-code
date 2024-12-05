package nl.sbmf21.aoc.common

import nl.sbmf21.aoc.common.Color.*

internal object Format {
    private const val SECONDS = 1_000_000_000.0
    private const val MILLIS = 1_000_000.0
    private const val MICROS = 1_000.0

    private const val SECONDS_SUFFIX = " s"
    private const val MILLIS_SUFFIX = "ms"
    private const val MICROS_SUFFIX = "μs"

    fun timeString(time: Long, trimSuffix: Boolean = false, colors: Boolean = true) = when {
        time >= SECONDS -> {
            val seconds = time / SECONDS
            val actualColor = when {
                !colors -> RESET
                seconds >= 5 -> RED
                else -> YELLOW
            }

            actualColor + format(time, SECONDS, SECONDS_SUFFIX, trimSuffix)
        }

        time >= MILLIS -> {
            val seconds = time / MILLIS
            val actualColor = when {
                !colors -> RESET
                seconds >= 500 -> YELLOW
                else -> RESET
            }

            actualColor + format(time, MILLIS, MILLIS_SUFFIX, trimSuffix)
        }

        time >= MICROS -> RESET + format(time, MICROS, MICROS_SUFFIX, trimSuffix)
        else -> "$RESET$time ns"
    }

    fun stringify(value: Any) = when (value) {
        is Long -> value.toBigInteger().toString()
        is Double -> value.toBigDecimal().toString()
        is NotImplementedError -> TODO
        is Throwable -> RED + value.stackTraceToString().replace("\t", "    ").trim()
        else -> value.toString()
    }

    private fun format(time: Long, value: Double, suffix: String, trimSuffix: Boolean) =
        "%,.2f".format(time / value) + "$RESET ${if (trimSuffix) suffix.trim() else suffix}"
}