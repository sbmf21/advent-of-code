package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.FinalDay

class Day25 : FinalDay() {

    override fun solution() = encode(input.map(::decode).sum())

    companion object {
        private const val DIGITS = "=-012"
        private const val BASE = 5
        private const val OFFSET = 2

        fun decode(snafu: String) = snafu.fold(0L) { n, c -> n * BASE + DIGITS.indexOf(c) - OFFSET }

        fun encode(decimal: Long) = buildString {
            var n = decimal
            while (n > 0) {
                val index = (n + OFFSET) % BASE
                append(DIGITS[index.toInt()])
                if (index < OFFSET) n += BASE
                n /= BASE
            }
        }.reversed()
    }
}