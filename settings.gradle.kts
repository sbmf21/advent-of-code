rootProject.name = "advent-of-code"

include(":aoc-commons")
include(":aoc-test-utils")

mapOf(
    "2015" to "2015",
    "2020" to "2020/kotlin",
    "2021" to "2021/kotlin",
    "2022" to "2022/kotlin",
    "2023" to "2023/kotlin",
).forEach { (name, folder) ->
    include(name)
    val project = project(":$name")
    project.projectDir = file(folder)
}