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
    processResources { from("../../input/2022/input") { into("input") } }
    processTestResources { from("../../input/2022/example") { into("example") } }
    withType<Jar> { archiveBaseName = "aoc2022" }
    withType<ShadowJar> {
        archiveClassifier = "shaded"
        mergeServiceFiles()
    }
    jar { dependsOn(shadowJar) }
    test { useJUnitPlatform() }
}

application {
    mainClass = "nl.sbmf21.aoc22.AocKt"
}