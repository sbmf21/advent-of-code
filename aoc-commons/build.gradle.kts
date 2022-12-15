import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
}

group = "nl.sbmf21"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    testImplementation(kotlin("test"))
}

tasks {
    withType<KotlinCompile> { kotlinOptions.jvmTarget = "17" }
    test { useJUnitPlatform() }
}
