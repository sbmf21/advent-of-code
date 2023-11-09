import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
}

val sbmfMathVersion: String by rootProject.extra

dependencies {
    implementation(project(":aoc-commons"))
    implementation("nl.sbmf21:math:$sbmfMathVersion")
    testImplementation(kotlin("test"))
}

tasks {
    withType<Jar> { archiveBaseName.set("aoc2020") }
    withType<ShadowJar> {
        archiveClassifier.set("shaded")
        mergeServiceFiles()
    }
    jar { dependsOn(shadowJar) }
    test { useJUnitPlatform() }
}

application {
    mainClass.set("nl.sbmf21.aoc20.AocKt")
}