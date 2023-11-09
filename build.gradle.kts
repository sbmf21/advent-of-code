plugins {
    kotlin("jvm") version "1.9.20" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
}

group = "nl.sbmf21.aoc"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "application")

    repositories {
        mavenCentral()
        maven("https://gitlab.sbmf21.nl/api/v4/groups/5/-/packages/maven") { name = "sbmf" }
    }

    tasks {
        withType<JavaCompile> { targetCompatibility = "17" }
    }
}