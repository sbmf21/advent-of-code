plugins {
    kotlin("jvm")
}

val sbmfMathVersion: String by rootProject.extra
val reflectionsVersion: String by rootProject.extra
val slf4jVersion: String by rootProject.extra

dependencies {
    implementation("nl.sbmf21:math:$sbmfMathVersion")
    implementation("org.reflections:reflections:$reflectionsVersion")
    implementation("org.slf4j:slf4j-nop:$slf4jVersion")
    testImplementation(kotlin("test"))
}

tasks {
    test { useJUnitPlatform() }
}