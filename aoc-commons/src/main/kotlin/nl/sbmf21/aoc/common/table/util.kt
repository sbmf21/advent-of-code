package nl.sbmf21.aoc.common.table

internal fun table(configure: Table.() -> Unit) = Table().apply(configure)