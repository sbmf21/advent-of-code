package nl.sbmf21.aoc.common.report

internal enum class Align(val render: (String, Int) -> String) {
    // LEFT({ value, width -> "$value${space(width - value.length)}" }),
    RIGHT({ value, width -> "${space(width - value.length)}$value" }),
    CENTER({ value, width ->
        val diff = width - value.length
        val left = diff / 2
        val right = diff - left

        "${space(left)}$value${space(right)}"
    })
}

internal fun space(len: Int): String = " ".repeat(len)
