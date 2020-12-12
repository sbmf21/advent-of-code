package io.frutsel_.aoc.report

internal class Column(val header: String, private val get: (Timing) -> String) {

    var len = initial()
        private set

    private fun initial() = header.length

    fun calculate(timings: List<Timing>): Int {
        var len = initial()

        timings.forEach {
            val valLen = get(it).length
            if (valLen > len) len = valLen
        }

        this.len = len

        return len
    }

    fun value(timing: Timing): String = get(timing)
}
