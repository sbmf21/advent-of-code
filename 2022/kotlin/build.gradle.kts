plugins {
    kotlin("jvm") version "1.7.21"
}

group = "nl.sbmf21"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://gitlab.com/api/v4/projects/22815243/packages/maven")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("nl.sbmf21:adventofcode.common:5.0.0")
}

tasks.test {
    useJUnitPlatform()
}
