package nl.sbmf21.aoc.common

data class DayMeta<T : ADay>(val clazz: Class<out T>) {
    val number = clazz.simpleName.substring("Day".length).toInt()

    fun build(example: Boolean = false): T = clazz
        .getDeclaredConstructor(List::class.java).newInstance(file(example))
        .also { it.number = this.number }

    private fun file(example: Boolean) = this::class.java
        .getResourceAsStream("/${if (example) "example" else "input"}/day$number.txt")!!
        .bufferedReader()
        .lines()
        .toArray()
        .map { it.toString() }
}
