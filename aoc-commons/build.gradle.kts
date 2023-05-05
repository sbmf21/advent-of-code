import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
}

group = "nl.sbmf21"

repositories {
    mavenCentral()
    maven("https://gitlab.sbmf21.nl/api/v4/projects/13/packages/maven") // sbmathf
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("nl.sbmf21:sbmathf:1.2.1")
    testImplementation(kotlin("test"))
}

tasks {
    withType<KotlinCompile> { kotlinOptions.jvmTarget = "17" }
    test { useJUnitPlatform() }
}
