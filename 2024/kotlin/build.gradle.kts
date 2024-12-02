import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
}

val sbmfMathVersion: String by rootProject.extra

dependencies {
    implementation(project(":aoc-commons"))
    implementation("nl.sbmf21:math:$sbmfMathVersion")
    testImplementation(kotlin("test"))
    testImplementation(project(":aoc-test-utils"))
}

tasks {
    processResources { from("../../advent-of-code-input/2024/input") { into("input") } }
    processTestResources { from("../../advent-of-code-input/2024/example") { into("example") } }
    withType<Jar> { archiveBaseName = "aoc2024" }
    withType<ShadowJar> {
        archiveClassifier = "shaded"
        mergeServiceFiles()
    }
    jar { dependsOn(shadowJar) }
    test { useJUnitPlatform() }
}

application {
    mainClass = "je.bouk.aoc24.AocKt"
}