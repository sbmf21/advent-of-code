import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
}

val annotationsVersion: String by rootProject.extra
val sbmfMathVersion: String by rootProject.extra
val junitJupiterVersion: String by rootProject.extra

dependencies {
    implementation(project(":aoc-commons"))
    implementation("org.jetbrains:annotations:$annotationsVersion")
    implementation("nl.sbmf21:math:$sbmfMathVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation(project(":aoc-test-utils"))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks {
    processResources { from("../../input/$year/input") { into("input") } }
    processTestResources { from("../../input/$year/example") { into("example") } }
    withType<Jar> { archiveBaseName = "aoc$year" }
    withType<ShadowJar> {
        archiveClassifier = "shaded"
        mergeServiceFiles()
    }
    jar { dependsOn(shadowJar) }
    test { useJUnitPlatform() }
}

application {
    mainClass = "$package.Aoc"
}