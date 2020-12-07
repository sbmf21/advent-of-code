package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.common.ADay
import java.util.regex.Pattern

class Day7(aoc: Aoc) : ADay(aoc) {

    private val myBag = "shiny gold"
    private val pattern = Pattern.compile("(?<bag>[\\w ]+) bags contain (?<rule>.*)\\.")
    private val bags = input.map {
        val matcher = pattern.matcher(it)
        matcher.matches()
        Bag(matcher.group("bag"), matcher.group("rule"))
    }

    override fun number(): Int = 7

    override fun part1(): Int = bags.filter { it.canFit(myBag, bags) }.count()

    override fun part2(): Int = bags.find { it.bag == myBag }?.count(bags)!!
}

internal open class Bag(var bag: String, private var rule: String) {
    private val pattern = Pattern.compile("(?<num>\\d+) (?<name>[\\w ]*) bags?")
    private val rules = rules()

    fun canFit(bag: String, bags: List<Bag>): Boolean {
        rules.forEach { rule ->
            if (rule.key == bag || bags.find { bag -> bag.bag == rule.key }?.canFit(bag, bags) == true) {
                return true
            }
        }

        return false
    }

    fun count(bags: List<Bag>): Int = rules.map { rule ->
        val bag = bags.find { bag -> bag.bag == rule.key }
        rule.value + if (bag!!.rules.isEmpty().not()) rule.value * bag.count(bags) else 0
    }.sum()

    private fun rules(): Map<String, Int> = if (rule == "no other bags") mapOf()
    else rule
        .split(", ")
        .map {
            val matcher = pattern.matcher(it)
            matcher.matches()

            matcher.group("name") to matcher.group("num").toInt()
        }.toMap()
}
