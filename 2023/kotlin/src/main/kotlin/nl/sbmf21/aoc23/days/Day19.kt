package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToInts
import nl.sbmf21.aoc.common.split
import nl.sbmf21.aoc23.TODO
import nl.sbmf21.aoc23.days.Day19.Action.*
import nl.sbmf21.aoc23.days.Day19.Check.*
import nl.sbmf21.aoc23.days.Day19.Rule.*
import kotlin.math.max
import kotlin.math.min

class Day19 : Day() {

    private val workflows: Map<String, Workflow>
    private val parts: List<Part>

    init {
        val (workflows, parts) = input.split(true, String::isBlank)

        this.workflows = workflows.associate { workflow ->
            val splitAt = workflow.indexOf('{')
            workflow.substring(0..<splitAt) to Workflow(workflow
                .substring(splitAt + 1..<workflow.lastIndex)
                .split(',')
                .map { rule ->
                    when {
                        rule.contains('>') -> rule.split('>', ':', limit = 3).let { (check, value, action) ->
                            GT(Check(check), value.toInt(), Action(action))
                        }

                        rule.contains('<') -> rule.split('<', ':', limit = 3).let { (check, value, action) ->
                            LT(Check(check), value.toInt(), Action(action))
                        }

                        else -> Execute(Action(rule))
                    }
                })
        }

        this.parts = parts.map { part ->
            val (x, m, a, s) = part.substring(1..<part.lastIndex)
                .split(',', limit = 4)
                .map { it.substring(2) }
                .mapToInts()

            Part(x, m, a, s)
        }
    }

    override fun part1() = parts.filter(::validate).sumOf(Part::sum)

    override fun part2(): Any {
        return TODO

        val range = 1L to 4000L

        var (minX, maxX) = range
        var (minM, maxM) = range
        var (minA, maxA) = range
        var (minS, maxS) = range

        val checked = mutableSetOf<String>()
        val toCheck = mutableListOf("in")

        while (toCheck.isNotEmpty()) {
            val current = workflows[toCheck.removeFirst().also(checked::add)]!!

            for (rule in current.rules) {
                when (rule) {
                    is GT -> {
                        if (rule.action == Reject) when (rule.check) {
                            X -> maxX = min(maxX, rule.value.toLong())
                            M -> maxM = min(maxM, rule.value.toLong())
                            A -> maxA = min(maxA, rule.value.toLong())
                            S -> maxS = min(maxS, rule.value.toLong())
                        }

                        if (rule.action is Forward) {
                            val to = rule.action.to
                            if (to !in checked && to !in toCheck) {
                                toCheck += to
                            }
                        }
                    }

                    is LT -> {
                        if (rule.action == Reject) when (rule.check) {
                            X -> minX = max(minX, rule.value.toLong())
                            M -> minM = max(minM, rule.value.toLong())
                            A -> minA = max(minA, rule.value.toLong())
                            S -> minS = max(minS, rule.value.toLong())
                        }

                        if (rule.action is Forward) {
                            val to = rule.action.to
                            if (to !in checked && to !in toCheck) {
                                toCheck += to
                            }
                        }
                    }

                    is Execute -> {
                        if (rule.action is Forward) {
                            val to = rule.action.to
                            if (to !in checked && to !in toCheck) {
                                toCheck += to
                            }
                        }
                        break
                    }
                }
            }
        }


        assert(maxX >= minX)
        assert(maxM >= minM)
        assert(maxA >= minA)
        assert(maxS >= minS)

        return (maxX - minX + 1) * (maxM - minM + 1) * (maxA - minA + 1) * (maxS - minS + 1)
    }

    private fun validate(part: Part): Boolean {
        var workflow = workflows["in"]!!

        while (true) {
            for (rule in workflow.rules) {
                return when (val action = rule(part) ?: continue) {
                    Accept -> true
                    Reject -> false
                    is Forward -> {
                        workflow = workflows[action.to]!!
                        break
                    }
                }
            }
        }
    }

    private class Workflow(val rules: List<Rule>)

    private sealed interface Rule {
        operator fun invoke(part: Part): Action?

        class GT(val check: Check, val value: Int, val action: Action) : Rule {
            override fun invoke(part: Part) = if (check(part) > value) action else null
        }

        class LT(val check: Check, val value: Int, val action: Action) : Rule {
            override fun invoke(part: Part) = if (check(part) < value) action else null
        }

        class Execute(val action: Action) : Rule {
            override fun invoke(part: Part) = action
        }
    }

    private sealed interface Action {
        data object Accept : Action
        data object Reject : Action
        data class Forward(val to: String) : Action

        companion object {
            operator fun invoke(input: String) = when (input) {
                "A" -> Accept
                "R" -> Reject
                else -> Forward(input)
            }
        }
    }

    private enum class Check(private val property: Part.() -> Int) {
        X(Part::x),
        M(Part::m),
        A(Part::a),
        S(Part::s);

        operator fun invoke(part: Part) = part.property()

        companion object {
            operator fun invoke(name: String) = entries.first { it.name.lowercase() == name }
        }
    }

    private class Part(val x: Int, val m: Int, val a: Int, val s: Int) {
        val sum = x + m + a + s
    }
}