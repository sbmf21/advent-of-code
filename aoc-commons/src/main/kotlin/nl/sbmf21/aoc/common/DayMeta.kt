package nl.sbmf21.aoc.common

data class DayMeta<T : ADay>(val clazz: Class<out T>) {
    val number = clazz.simpleName.substring("Day".length).toInt()

    fun build(example: Boolean = false, filename: String? = null): T = clazz
        .getDeclaredConstructor(List::class.java).newInstance(file(example, filename))
        .also { it.number = this.number }

    private fun file(example: Boolean, filename: String?) = this::class.java
        .getResourceAsStream("/${if (example) "example" else "input"}/day$number${if (filename == null) "" else "-$filename"}.txt")!!
        .bufferedReader()
        .lines()
        .toList()
}
