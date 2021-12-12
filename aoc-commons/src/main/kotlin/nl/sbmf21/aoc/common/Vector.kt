package nl.sbmf21.aoc.common

data class Vector3(val x: Int, val y: Int, val z: Int) {
    override fun equals(other: Any?) = other is Vector3 && x == other.x && y == other.y && z == other.z
    override fun hashCode() = "$x $y $z".hashCode()
}

data class Vector4(val x: Int, val y: Int, val z: Int, val w: Int) {
    override fun equals(other: Any?) = other is Vector4 && x == other.x && y == other.y && z == other.z && w == other.w
    override fun hashCode() = "$x $y $z $w".hashCode()
}
