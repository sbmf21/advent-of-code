package io.frutsel_.aoc.common

import org.reflections.Reflections
import java.util.regex.Pattern

abstract class AocBase(val name: String) {

    private var pattern = Pattern.compile("--day(?:=(?<day>\\d+))?")
    private var runDay: Int? = null

    fun init(args: Array<String>) = args.forEach {
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

    fun findDays(): List<ADay> = Reflections("${this::class.java.packageName}.days")
        .getSubTypesOf(ADay::class.java)
        .map { it.getDeclaredConstructor(this::class.java).newInstance(this) }
        .sortedBy { it.number() }
        .filter { if (runDay == null) true else it.number() == runDay }

    fun file(day: ADay): List<String> = day.javaClass
        .getResourceAsStream("/input/day${day.number()}.txt")
        .bufferedReader()
        .lines()
        .toArray()
        .map { it.toString() }
}
