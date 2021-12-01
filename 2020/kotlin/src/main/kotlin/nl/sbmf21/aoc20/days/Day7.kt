package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc20.Aoc
import java.util.regex.Pattern

class Day7(aoc: Aoc, number: Int) : ADay(aoc, number) {

    private val myBag = "shiny gold"
    private val pattern = Pattern.compile("(?<bag>[\\w ]+) bags contain (?<rule>.*)\\.")
    private val bags = input.map {
        val matcher = pattern.matcher(it)
        matcher.matches()
        Bag(matcher.group("bag"), matcher.group("rule"))
    }

    override fun part1() = bags.count { it.canFit(myBag, bags) }

    override fun part2() = bags.find { it.bag == myBag }?.count(bags)!!
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

    private fun rules() = if (rule == "no other bags") mapOf() else rule
        .split(", ")
        .associate {
            val matcher = pattern.matcher(it)
            matcher.matches()

            matcher.group("name") to matcher.group("num").toInt()
        }
}
