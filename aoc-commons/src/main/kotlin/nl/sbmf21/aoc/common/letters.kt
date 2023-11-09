package nl.sbmf21.aoc.common

val AOC_LETTERS = listOf(
    Pair(
        'A', listOf(
            listOf(0, 1, 1, 0), //  ██
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 1, 1, 1), // ████
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
        )
    ),
    Pair(
        'B', listOf(
            listOf(1, 1, 1, 0), // ███
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 1, 1, 0), // ███
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 1, 1, 0), // ███
        )
    ),
    Pair(
        'C', listOf(
            listOf(0, 1, 1, 0), //  ██
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 1), // █  █
            listOf(0, 1, 1, 0), //  ██
        )
    ),
    Pair('D', listOf()), // █ UNKNOWN █
    Pair(
        'E', listOf(
            listOf(1, 1, 1, 1), // ████
            listOf(1, 0, 0, 0), // █
            listOf(1, 1, 1, 0), // ███
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
            listOf(1, 1, 1, 1), // ████
        )
    ),
    Pair(
        'F', listOf(
            listOf(1, 1, 1, 1), // ████
            listOf(1, 0, 0, 0), // █
            listOf(1, 1, 1, 0), // ███
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
        )
    ),
    Pair(
        'G', listOf(
            listOf(0, 1, 1, 0), //  ██
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 1, 1), // █ ██
            listOf(1, 0, 0, 1), // █  █
            listOf(0, 1, 1, 1), //  ███
        )
    ),
    Pair(
        'H', listOf(
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 1, 1, 1), // ████
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
        )
    ),
    Pair('I', listOf()), // █ UNKNOWN █
    Pair(
        'J', listOf(
            listOf(0, 0, 1, 1), //   ██
            listOf(0, 0, 0, 1), //    █
            listOf(0, 0, 0, 1), //    █
            listOf(0, 0, 0, 1), //    █
            listOf(1, 0, 0, 1), // █  █
            listOf(0, 1, 1, 0), //  ██
        )
    ),
    Pair(
        'K', listOf(
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 1, 0), // █ █
            listOf(1, 1, 0, 0), // ██
            listOf(1, 0, 1, 0), // █ █
            listOf(1, 0, 1, 0), // █ █
            listOf(1, 0, 0, 1), // █  █
        )
    ),
    Pair(
        'L', listOf(
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
            listOf(1, 1, 1, 1), // ████
        )
    ),
    Pair('M', listOf()), // █ UNKNOWN █
    Pair('N', listOf()), // █ UNKNOWN █
    Pair(
        'O', listOf(
            listOf(0, 1, 1, 0), //  ██
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(0, 1, 1, 0), //  ██
        )
    ),
    Pair(
        'P', listOf(
            listOf(1, 1, 1, 0), // ███
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 1, 1, 0), // ███
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
        )
    ),
    Pair('Q', listOf()), // █ UNKNOWN █
    Pair(
        'R', listOf(
            listOf(1, 1, 1, 0), // ███
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 1, 1, 0), // ███
            listOf(1, 0, 1, 0), // █ █
            listOf(1, 0, 0, 1), // █  █
        )
    ),
    Pair(
        'S', listOf(
            listOf(0, 1, 1, 1), //  ███
            listOf(1, 0, 0, 0), // █
            listOf(1, 0, 0, 0), // █
            listOf(0, 1, 1, 0), //  ██
            listOf(0, 0, 0, 1), //    █
            listOf(1, 1, 1, 0), // ███
        )
    ),
    Pair('T', listOf()), // █ UNKNOWN █
    Pair(
        'U', listOf(
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(1, 0, 0, 1), // █  █
            listOf(0, 1, 1, 0), //  ██
        )
    ),
    Pair('V', listOf()), // █ UNKNOWN █
    Pair('W', listOf()), // █ UNKNOWN █
    Pair('X', listOf()), // █ UNKNOWN █
    Pair( // FIVE WIDE?????
        'Y', listOf(
            listOf(1, 0, 0, 0, 1), // █   █
            listOf(1, 0, 0, 0, 1), // █   █
            listOf(0, 1, 0, 1, 0), //  █ █
            listOf(0, 0, 1, 0, 0), //   █
            listOf(0, 0, 1, 0, 0), //   █
            listOf(0, 0, 1, 0, 0), //   █
        )
    ),
    Pair(
        'Z', listOf(
            listOf(1, 1, 1, 1), // ████
            listOf(0, 0, 0, 1), //    █
            listOf(0, 0, 1, 0), //   █
            listOf(0, 1, 0, 0), //  █
            listOf(1, 0, 0, 0), // █
            listOf(1, 1, 1, 1), // ████
        )
    ),
)

fun aocLetter(letter: List<List<Int>>): Char? {
    val data = if (letter.none { it.size > 4 && it[4] == 1 }) letter.map { it.subList(0, 4) } else letter
    return AOC_LETTERS.firstOrNull { it.second == data }?.first
}