package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.prod
import nl.sbmf21.aoc22.days.Blueprint.*
import nl.sbmf21.aoc22.days.GeodeState.Minerals
import nl.sbmf21.aoc22.days.GeodeState.Robots
import nl.sbmf21.aoc22.days.MineralMiningRobot.*
import kotlin.math.max
import kotlin.math.min

class Day19(input: List<String>) : ADay(input) {

    private val blueprints = input.map { it.split(" ") }.map { parts ->
        Blueprint(
            parts[1].substring(0, parts[1].length - 1).toInt(),
            OreBlueprint(parts[6].toInt()),
            ClayBlueprint(parts[12].toInt()),
            ObsidianBlueprint(parts[18].toInt(), parts[21].toInt()),
            GeodeBlueprint(parts[27].toInt(), parts[30].toInt()),
        )
    }

    override fun part1() = run(24, blueprints).sumOf { it.first.id * it.second }

    override fun part2() = run(32, blueprints.subList(0, min(blueprints.size, 3))).map { it.second }.prod()

    private fun run(minutes: Int, blueprints: List<Blueprint>) = blueprints.mapTo(mutableListOf()) { blueprint ->
        var maxGeodes = 0
        repeat(500_000) { _ ->
            val state = GeodeState(blueprint, Robots(), Minerals())

            repeat(minutes) { _ ->
                val building = state.build()

                state.minerals.ore += state.robots.ore
                state.minerals.clay += state.robots.clay
                state.minerals.obsidian += state.robots.obsidian
                state.minerals.geode += state.robots.geode

                if (building != null) when (building) {
                    ORE -> {
                        state.minerals.ore -= blueprint.ore.oreCost
                        state.robots.ore++
                    }

                    CLAY -> {
                        state.minerals.ore -= blueprint.clay.oreCost
                        state.robots.clay++
                    }

                    OBSIDIAN -> {
                        state.minerals.ore -= blueprint.obsidian.oreCost
                        state.minerals.clay -= blueprint.obsidian.clayCost
                        state.robots.obsidian++
                    }

                    GEODE -> {
                        state.minerals.ore -= blueprint.geode.oreCost
                        state.minerals.obsidian -= blueprint.geode.obsidianCost
                        state.robots.geode++
                    }
                }
            }
            maxGeodes = max(maxGeodes, state.minerals.geode)
        }

        blueprint to maxGeodes
    }
}

private enum class MineralMiningRobot {
    ORE,
    CLAY,
    OBSIDIAN,
    GEODE;
}

private data class GeodeState(
    val blueprint: Blueprint,
    var robots: Robots,
    var minerals: Minerals,
) {
    private val maxOreRequired = listOf(
        blueprint.clay.oreCost,
        blueprint.obsidian.oreCost,
        blueprint.geode.oreCost,
    ).max()

    fun build() = when {
        canBuildGeode() -> GEODE
        canBuildObsidian() -> OBSIDIAN
        canBuildClay() -> CLAY
        canBuildOre() -> ORE
        else -> null
    }

    private fun canBuildGeode(): Boolean {
        return minerals.ore >= blueprint.geode.oreCost
            && minerals.obsidian >= blueprint.geode.obsidianCost
    }

    private fun canBuildObsidian(): Boolean {
        return robots.obsidian <= blueprint.geode.obsidianCost
            && minerals.ore >= blueprint.obsidian.oreCost
            && minerals.clay >= blueprint.obsidian.clayCost
            && ehWhatever()
    }

    private fun canBuildClay(): Boolean {
        return robots.clay <= blueprint.obsidian.clayCost
            && minerals.ore >= blueprint.clay.oreCost
            && ehWhatever()
    }

    private fun canBuildOre(): Boolean {
        return robots.ore <= maxOreRequired
            && minerals.ore >= blueprint.ore.oreCost
            && ehWhatever()
    }

    private fun ehWhatever(): Boolean = listOf(0, 1).random() == 1

    class Robots(var ore: Int = 1, var clay: Int = 0, var obsidian: Int = 0, var geode: Int = 0)
    data class Minerals(var ore: Int = 0, var clay: Int = 0, var obsidian: Int = 0, var geode: Int = 0)
}

private data class Blueprint(
    val id: Int,
    val ore: OreBlueprint,
    val clay: ClayBlueprint,
    val obsidian: ObsidianBlueprint,
    val geode: GeodeBlueprint,
) {
    data class OreBlueprint(val oreCost: Int)
    data class ClayBlueprint(val oreCost: Int)
    data class ObsidianBlueprint(val oreCost: Int, val clayCost: Int)
    data class GeodeBlueprint(val oreCost: Int, val obsidianCost: Int)
}