import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":aoc-commons"))
    implementation("nl.sbmf21:math:1.4.0")
    testImplementation(kotlin("test"))
}

tasks {
    withType<Jar> { archiveBaseName.set("aoc2022") }
    withType<ShadowJar> {
        archiveClassifier.set("shaded")
        mergeServiceFiles()
    }
    jar { dependsOn(shadowJar) }
    test { useJUnitPlatform() }
}

application {
    mainClass.set("nl.sbmf21.aoc22.AocKt")
}