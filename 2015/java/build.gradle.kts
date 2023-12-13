import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
}

val annotationsVersion: String by rootProject.extra
val gsonVersion: String by rootProject.extra
val junitJupiterVersion: String by rootProject.extra

dependencies {
    implementation(project(":aoc-commons"))
    implementation("org.jetbrains:annotations:$annotationsVersion")
    implementation("com.google.code.gson:gson:$gsonVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation(project(":aoc-test-utils"))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks {
    processResources { from("../resources/input") { into("input") } }
    processTestResources { from("../resources/example") { into("example") } }
    withType<Jar> { archiveBaseName = "aoc2015" }
    withType<ShadowJar> {
        archiveClassifier = "shaded"
        mergeServiceFiles()
    }
    jar { dependsOn(shadowJar) }
    test { useJUnitPlatform() }
}

application {
    mainClass = "nl.sbmf21.aoc15.Aoc"
}