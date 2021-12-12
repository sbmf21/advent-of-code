package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay

internal data class Vector3(val x: Int, val y: Int, val z: Int) {
    override fun equals(other: Any?) = other is Vector3 && x == other.x && y == other.y && z == other.z
    override fun hashCode() = "$x $y $z".hashCode()
}

internal data class Vector4(val x: Int, val y: Int, val z: Int, val w: Int) {
    override fun equals(other: Any?) = other is Vector4 && x == other.x && y == other.y && z == other.z && w == other.w
    override fun hashCode() = "$x $y $z $w".hashCode()
}

class Day17(input: List<String>) : ADay(input) {

    private val bootRange = -12..12
    private val neighbors = -1..1

    override fun part1(): Int {

        var actives = mutableSetOf<Vector3>()
        for (r in input.indices) for (c in input[r].indices) if (input[r][c] == '#') actives.add(Vector3(r, c, 0))

        for (cycle in 1..6) {
            val newActives = mutableSetOf<Vector3>()

            for (x in bootRange) for (y in bootRange) for (z in bootRange) {
                var nbr = 0

                for (dx in neighbors) for (dy in neighbors) for (dz in neighbors) {
                    if (dx == 0 && dy == 0 && dz == 0) continue
                    if (actives.contains(Vector3(x + dx, y + dy, z + dz))) nbr++
                }

                val current = Vector3(x, y, z)
                if (!actives.contains(current) && nbr == 3) newActives.add(current)
                if (actives.contains(current) && nbr in 2..3) newActives.add(current)
            }

            actives = newActives
        }

        return actives.size
    }

    override fun part2(): Any {
        var actives = mutableSetOf<Vector4>()
        for (r in input.indices) for (c in input[r].indices) if (input[r][c] == '#') actives.add(Vector4(r, c, 0, 0))

        for (cycle in 1..6) {
            val newActives = mutableSetOf<Vector4>()

            for (x in bootRange) for (y in bootRange) for (z in bootRange) for (w in bootRange) {
                var count = 0

                for (dx in neighbors) for (dy in neighbors) for (dz in neighbors) for (dw in neighbors) {
                    if (dx == 0 && dy == 0 && dz == 0 && dw == 0) continue
                    if (actives.contains(Vector4(x + dx, y + dy, z + dz, w + dw))) count++
                }

                val current = Vector4(x, y, z, w)
                if (actives.contains(current) && count in 2..3) newActives.add(current)
                if (!actives.contains(current) && count == 3) newActives.add(current)
            }

            actives = newActives
        }

        return actives.size
    }
}
