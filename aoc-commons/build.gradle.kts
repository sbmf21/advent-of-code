plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("nl.sbmf21:math:1.4.0")
    testImplementation(kotlin("test"))
}

tasks {
    test { useJUnitPlatform() }
}