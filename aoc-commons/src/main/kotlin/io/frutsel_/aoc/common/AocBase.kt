package io.frutsel_.aoc.common

import org.reflections.Reflections

abstract class AocBase {

    fun findDays() = Reflections("${this::class.java.packageName}.days")
        .getSubTypesOf(ADay::class.java)
        .map { it.getDeclaredConstructor(this::class.java).newInstance(this) }
        .sortedBy { it.number() }

    fun file(day: ADay): List<String> = day.javaClass
        .getResourceAsStream("/input/day${day.number()}.txt")
        .bufferedReader()
        .lines()
        .toArray()
        .map { it.toString() }
}
