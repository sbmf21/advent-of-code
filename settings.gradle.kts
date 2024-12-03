rootProject.name = "advent-of-code"

include(":aoc-commons")
include(":aoc-test-utils")

listOf(
    // @projects:start
    "2015:java",
    "2016:kotlin",
    "2020:kotlin",
    "2021:kotlin",
    "2022:kotlin",
    "2023:kotlin",
    "2024:kotlin",
    // @projects:end
).forEach {
    val (year, language) = it.split(":")
    include("$year@$language")
    project(":$year@$language").apply {
        projectDir = file("$year/$language")
    }
}