package nl.sbmf21.aoc.common

data class PuzzleMeta<T : Puzzle>(val clazz: Class<out T>) {

    val number = clazz.simpleName.substring("Day".length).toInt()

    fun build(example: Boolean = false, filename: String? = null): T = build(file(example, filename))

    fun build(input: List<String>): T {
        Cache[clazz] = number to input
        return clazz
            .getDeclaredConstructor()
            .newInstance()
    }

    private fun file(example: Boolean, filename: String?) = this::class.java
        .getResourceAsStream("/${if (example) "example" else "input"}/day$number${if (filename == null) "" else "-$filename"}.txt")!!
        .bufferedReader()
        .lines()
        .toList()
}