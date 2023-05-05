import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

group = "nl.sbmf21"

repositories {
    mavenCentral()
    maven("https://gitlab.sbmf21.nl/api/v4/projects/13/packages/maven") // sbmathf
}

dependencies {
    implementation(project(":commons"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks {
    withType<JavaCompile> { targetCompatibility = "17" }
    withType<Jar> { archiveBaseName.set("aoc2015") }
    withType<ShadowJar> {
        archiveClassifier.set("shaded")
        mergeServiceFiles()
    }
    jar { dependsOn(shadowJar) }
    test { useJUnitPlatform() }
}

application {
    mainClass.set("nl.sbmf21.aoc15.Aoc")
}
