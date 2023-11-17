package nl.sbmf21.aoc.common

sealed class Puzzle : Comparable<Puzzle> {
    val number: Int
    val input: List<String>

    init {
        Cache[this::class.java].let { (number, input) ->
            this.number = number
            this.input = input
        }
    }

    override fun compareTo(other: Puzzle) = number.compareTo(other.number)
}