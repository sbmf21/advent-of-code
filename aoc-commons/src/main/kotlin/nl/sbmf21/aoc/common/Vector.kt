package nl.sbmf21.aoc.common

data class Vector2(val x: Int, val y: Int) {
    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)
    operator fun minus(other: Vector2) = Vector2(x - other.x, y - other.y)
    override fun equals(other: Any?) = other is Vector2 && x == other.x && y == other.y
    override fun hashCode() = "$x $y".hashCode()
}

data class Vector3(val x: Int, val y: Int, val z: Int) {
    operator fun plus(other: Vector3) = Vector3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vector3) = Vector3(x - other.x, y - other.y, z - other.z)
    override fun equals(other: Any?) = other is Vector3 && x == other.x && y == other.y && z == other.z
    override fun hashCode() = "$x $y $z".hashCode()
}

data class Vector4(val x: Int, val y: Int, val z: Int, val w: Int) {
    override fun equals(other: Any?) = other is Vector4 && x == other.x && y == other.y && z == other.z && w == other.w
    override fun hashCode() = "$x $y $z $w".hashCode()
}
