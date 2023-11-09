package nl.sbmf21.aoc21.days

internal fun MatchResult.toInt(name: String) = this.groups[name]!!.value.toInt()