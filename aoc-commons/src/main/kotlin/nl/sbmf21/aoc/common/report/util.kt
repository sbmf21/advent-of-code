package nl.sbmf21.aoc.common.report

internal fun table(configure: Table.() -> Unit) = Table().apply(configure)