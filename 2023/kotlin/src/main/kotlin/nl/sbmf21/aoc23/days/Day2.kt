package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day

class Day2 : Day() {

    private val games = input.map { game ->
        val p1 = game.split(": ", limit = 2)
        Game(p1[0].split(" ")[1].toInt(), p1[1].split("; ").map { set ->
            set.split(", ")
                .associate { c -> c.split(" ", limit = 2).let { it[1] to it[0].toInt() } }
                .let { Set(it["red"] ?: 0, it["green"] ?: 0, it["blue"] ?: 0) }
        })
    }

    override fun part1() = sum { game, r, g, b -> if (r <= 12 && g <= 13 && b <= 14) game.id else 0 }
    override fun part2() = sum { _, r, g, b -> r * g * b }

    private fun sum(value: (Game, Int, Int, Int) -> Int) = games.sumOf { game ->
        val red = game.sets.maxOf(Set::red)
        val green = game.sets.maxOf(Set::green)
        val blue = game.sets.maxOf(Set::blue)
        value(game, red, green, blue)
    }

    private data class Game(val id: Int, val sets: List<Set>)
    private data class Set(val red: Int, val green: Int, val blue: Int)
}