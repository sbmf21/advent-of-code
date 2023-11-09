plugins {
    kotlin("jvm")
}

val sbmfMathVersion: String by rootProject.extra
val reflectionsVersion: String by rootProject.extra

dependencies {
    implementation("nl.sbmf21:math:$sbmfMathVersion")
    implementation("org.reflections:reflections:$reflectionsVersion")
    testImplementation(kotlin("test"))
}

tasks {
    test { useJUnitPlatform() }
}