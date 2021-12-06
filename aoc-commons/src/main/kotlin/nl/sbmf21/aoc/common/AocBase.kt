package nl.sbmf21.aoc.common

import org.reflections.Reflections
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.regex.Pattern

abstract class AocBase(val name: String) {

    private var report: Report = makeReport()
    private var pattern = Pattern.compile("--day(?:=(?<day>\\d+))?")
    internal var runDay: Int? = null
    val days = Reflections("${this::class.java.packageName}.days")
        .getSubTypesOf(ADay::class.java)
        .filter { it.simpleName.matches(Regex("Day\\d+")) }
        .map { DayMeta(it) }
        .sortedBy { it.number }

    fun exec(args: Array<String>) {
        init(args)
        runDays()
        report()
    }

    internal fun init(args: Array<String>, inputStream: InputStream = System.`in`) = args.forEach {
        val matcher = pattern.matcher(it)

        if (matcher.matches()) {
            val day = matcher.group("day")

            if (day == null) {
                print("Enter day to run: ")

                this.runDay = BufferedReader(InputStreamReader(inputStream)).readLine()?.toInt()
            } else {
                this.runDay = day.toInt()
            }
        }

        if (it.matches(Regex("--latest"))) {
            this.runDay = days.last().number
        }
    }

    internal fun runDays() = days
        .filter { runDay == null || it.clazz.simpleName.equals("Day$runDay") }
        .map { it.build() }
        .onEach { report.time(it) }

    private fun report() = report.render()
    private fun makeReport() = Report(this)
}
