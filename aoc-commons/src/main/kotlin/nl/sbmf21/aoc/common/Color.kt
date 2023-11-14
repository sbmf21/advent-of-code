package nl.sbmf21.aoc.common

/**
 * ASCII colors https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html
 */
enum class Color(private val code: String) {
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    LIGHT_GREEN("\u001b[38;5;46m"),
    GRAY("\u001B[38;5;245m"),
    WHITE("\u001b[38;5;255m"),

    BACK_DARK_RED("\u001B[48;5;52m"),
    BACK_DARK_GREEN("\u001b[48;5;22m"),
    BACK_BLACK("\u001b[48;5;0m"),
    BACK_WHITE("\u001b[48;5;15m"),
    RESET("\u001b[0m");

    operator fun plus(string: String) = code + string

    override fun toString() = code
}