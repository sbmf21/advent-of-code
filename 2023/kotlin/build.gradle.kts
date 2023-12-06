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
    withType<Jar> { archiveBaseName.set("aoc2023") }
    withType<ShadowJar> {
        archiveClassifier.set("shaded")
        mergeServiceFiles()
    }
    jar { dependsOn(shadowJar) }
    test { useJUnitPlatform() }
}

application {
    mainClass.set("nl.sbmf21.aoc23.AocKt")
}