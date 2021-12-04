package nl.sbmf21.aoc.common

import nl.sbmf21.aoc.common.report.Report
import org.reflections.Reflections
import java.util.regex.Pattern

abstract class AocBase(val name: String) {

    private var report: Report = makeReport()
    private var pattern = Pattern.compile("--day(?:=(?<day>\\d+))?")
    private var runDay: Int? = null

    fun exec(args: Array<String>) {
        init(args)
        runDays()
        report()
    }

    fun findDays() = Reflections("${this::class.java.packageName}.days").getSubTypesOf(ADay::class.java)

    private fun init(args: Array<String>) = args.forEach {
        val matcher = pattern.matcher(it)

        if (matcher.matches()) {
            val day = matcher.group("day")

            if (day == null) {
                print("Enter day to run: ")

                this.runDay = readLine()?.toInt()
            } else {
                this.runDay = day.toInt()
            }
        }
    }

    private fun runDays() = findDays()
        .filter { runDay == null || it.simpleName.equals("Day$runDay") }
        .map { buildDay(it) }
        .sortedBy { it.number }
        .forEach { report.time(it) }

    private fun report() = report.render()
    private fun makeReport() = Report(this)
}

fun buildDay(cls: Class<out ADay>, example: Boolean = false) = cls
    .getDeclaredConstructor(List::class.java).newInstance(file(cls, example))
    .apply { number = number(cls) }

fun file(cls: Class<out ADay>, example: Boolean) = cls
    .getResourceAsStream("/${folder(example)}/day${number(cls)}.txt")!!
    .bufferedReader()
    .lines()
    .toArray()
    .map { it.toString() }

fun number(cls: Class<out ADay>) = cls.simpleName.substring("Day".length).toInt()

private fun folder(example: Boolean) = if (example) "example" else "input"
