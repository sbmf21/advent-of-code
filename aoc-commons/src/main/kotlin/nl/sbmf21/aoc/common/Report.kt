package nl.sbmf21.aoc.common

internal fun space(len: Int) = " ".repeat(len)

internal class Report(private val aoc: AocBase) {

    private val builder = StringBuilder()
    private val timings = mutableListOf<TimedRunner>()
    private val columns = listOf(
        Column("Day") { it.meta.number.toString() },
        // Timings
        Column("Total") { it.totalTime() },
        Column("Setup") { it.setupTime() },
        Column("Part 1") { it.part1Time() },
        Column("Part 2") { it.part2Time() },
        // Values
        Column("Part 1") { it.part1Value() },
        Column("Part 2") { it.part2Value() }
    )

    fun run(meta: DayMeta<ADay>) = TimedRunner(meta).also { timings.add(it); it.run() }.day()

    fun render() {
        val sizes = calculateSizes()

        addTitle(sizes)
        addHeaders()
        addColumnHeaders(sizes)

        if (timings.size > 0) {
            timings.forEach { addTiming(it) }
            addLine(sizes.lineSizes, '┴', '└', '┘')
        } else addNone(sizes)

        print(builder)
        builder.clear()
    }

    private fun addTitle(sizes: Sizes) {
        val title = listOf(cell("* Advent of Code ${aoc.name} *", sizes.totalWidth - 2, Align.CENTER))
        addLine(title.map { it.length }, '┬', '┌', '┐')
        add(title)
    }

    private fun addNone(sizes: Sizes) {
        val title = listOf(cell("No days have ran.", sizes.totalWidth - 2, Align.CENTER))
        add(title)
        addLine(title.map { it.length }, '┴', '└', '┘')
    }

    private fun addHeaders() {
        val l = listOf(
            cell("Day", columns[0].len, Align.CENTER),
            cell("Timings", columns.subList(1, 5).sumOf { it.len } + 9, Align.CENTER),
            cell("Answers", columns.subList(5, 7).sumOf { it.len } + 3, Align.CENTER)
        )

        addLine(l.map { it.length }, '┬')
        add(l)
    }

    private fun addColumnHeaders(sizes: Sizes) {
        addLine(sizes.lineSizes)
        add(columns.map { cell(it.header, it.len, Align.CENTER) })
        addLine(sizes.lineSizes)
    }

    private fun addLine(sizes: List<Int>, sep: Char = '┼', left: Char = '├', right: Char = '┤') {
        builder
            .append(join(sizes.map { "─".repeat(it) }, '─', sep, left, right))
            .appendLine()
    }

    private fun addTiming(timing: TimedRunner) {
        add(columns.map { cell(it.value(timing), it.len, Align.RIGHT) })
    }

    private fun add(line: List<String>) {
        builder.append(join(line)).appendLine()
    }

    private fun cell(value: String, width: Int, align: Align): String = align.render(value, width)

    private fun join(
        line: List<String>,
        spacer: Char = ' ',
        sep: Char = '│',
        left: Char = sep,
        right: Char = sep
    ): String = listOf(
        "$left$spacer",
        line.joinToString(separator = "$spacer$sep$spacer"),
        "$spacer$right"
    ).joinToString(separator = "")

    private fun calculateSizes(): Sizes {
        columns.forEach { it.calculate(timings) }

        return Sizes(columns)
    }
}

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

internal class Column(val header: String, private val get: (TimedRunner) -> String) {

    var len = initial()
        private set

    private fun initial() = header.length

    fun calculate(timings: List<TimedRunner>): Int {
        var len = initial()

        timings.forEach {
            val valLen = get(it).length
            if (valLen > len) len = valLen
        }

        this.len = len

        return len
    }

    fun value(timing: TimedRunner) = get(timing)
}

internal class Sizes(private var columns: List<Column>) {

    var totalWidth = totalWidth()
    var lineSizes = lineSizes()

    private fun totalWidth() = columns.sumOf { it.len } + columns.size * 3 - 1

    private fun lineSizes() = columns.map { it.len }
}
