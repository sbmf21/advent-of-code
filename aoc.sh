#!/bin/bash

function check_arguments {
  if [ "$#" -ne 3 ]; then
    me=$(basename "$0")
    echo -e "\e[31mUsage: ./$me year language day" >&2; exit 1
  fi
}

function check_year {
  regex='^(20(1[5-9]|[2-9][0-9]))|(2[1-9][0-9]{2})$'
  if ! [[ "$1" =~ $regex ]] ; then
    echo -e "\e[31mError: $1 is not a valid year" >&2; exit 1
  fi
}

function check_day {
  regex='^((2[0-5])|(1[0-9])|([1-9]))$'
  if ! [[ "$1" =~ $regex ]] ; then
    echo -e "\e[31mError: $1 is not a valid day" >&2; exit 1
  fi
}

function base_folder {
  folder=$(dirname "$0")
  echo "$folder"
}

function init_folder {
  base=$(base_folder)
  folder="$base/$1/$2"
  mkdir -p "$folder"
  echo "$folder"
}

function create_file {
  if [ -s "$1" ]; then return 1; fi

  echo "Generating $1"
  mkdir -p "${1%/*}" && touch "$1"

  if [ -z "$2" ]; then return 1; fi

  echo "$2" >> "$1"
  return 0
}

function create_gradle_ci {
    if create_file "$3/.gitlab-ci.yml" "$(cat <<CI
.$1:$2:caches:
  key: '$1:$2'
  paths:
    - .gradle
    - aoc-commons/build
    - $1/$2/build

.$1:$2:
  extends: .gradle
  only:
    changes:
      - aoc-commons/**/*
      - aoc-test-utils/**/*
      - $1/$2/**/*
  cache:
    key: !reference [ .$1:$2:caches, key ]
    policy: pull
    paths: !reference [ .$1:$2:caches, paths ]

$1:$2:compile:
  extends:
    - .$1:$2
    - .gradle:compile
  variables:
    PROJECT: '$1'
  cache:
    key: !reference [ .$1:$2:caches, key ]
    policy: pull-push
    paths: !reference [ .$1:$2:caches, paths ]

$1:$2:test:
  extends:
    - .$1:$2
    - .gradle:test
  variables:
    PROJECT: '$1'
  dependencies:
    - $1:$2:compile
  needs:
    - $1:$2:compile
  artifacts:
    reports:
      junit: $1/$2/build/test-results/test/TEST-*.xml

$1:$2:run:
  extends: .$1:$2
  stage: run
  dependencies:
    - $1:$2:compile
  needs:
    - $1:$2:compile
    - $1:$2:test
  script:
    - java -jar $1/$2/build/libs/aoc$1-shaded.jar
CI
  )"; then
    echo -e "\e[32mHEY: Remember to add \`\e[48;5;0m- local: /$1/$2/.gitlab-ci.yml\e[0m\e[32m\` to the gitlab ci configuration\e[0m"
    return 0
  fi

  return 1
}

function run_gradle {
  cd "$(base_folder)" || exit 1

  echo "Running tests"
  if ! ./gradlew ":$2:clean" ":$2:test" --tests "$packageBase.aoc${2:(-2)}.days.Day${3}Test"; then
    echo -e "\e[31mTests failed" >&2; return
  fi

  echo "Compiling application"
  if ! ./gradlew ":$2:jar"; then
    echo -e "\e[31mCompilation failed" >&2; return
  fi

  echo "Running application"
  java -jar "$2/$1/build/libs/aoc$2-shaded.jar" --day="$day"
}

check_arguments "$@"

year=$1
day=$3

check_year "$year"
check_day "$day"

if (( "$year" >= 2024 )); then
  sourceBase="je/bouk"
  packageBase="je.bouk"
else
  sourceBase="nl/sbmf21"
  packageBase="nl.sbmf21"
fi

case $2 in
  "kotlin")
    echo "Generating kotlin files for $year:$day"
    folder=$(init_folder "$year" "kotlin")
    packageFolder="$sourceBase/aoc${year:(-2)}"
    package="$packageBase.aoc${year:(-2)}"

    projectFile="$folder/build.gradle.kts"
    mainFile="$folder/src/main/kotlin/$packageFolder/Aoc.kt"
    solutionFile="$folder/src/main/kotlin/$packageFolder/days/Day${day}.kt"
    testFile="$folder/src/test/kotlin/$packageFolder/days/Day${day}Test.kt"

    needs_action=0

    if create_gradle_ci "$year" "kotlin" "$folder"; then needs_action=1; fi

    if create_file "$projectFile" "$(cat <<GRADLE
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
}

val sbmfMathVersion: String by rootProject.extra

dependencies {
    implementation(project(":aoc-commons"))
    implementation("nl.sbmf21:math:\$sbmfMathVersion")
    testImplementation(kotlin("test"))
    testImplementation(project(":aoc-test-utils"))
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
    mainClass = "$packageBase.aoc${year:(-2)}.AocKt"
}
GRADLE
    )"; then
      truncate -s -1 "$projectFile"
      echo -e "\e[32mHEY: Remember to add \`\e[48;5;0m\"$year\" \e[34mto \e[32m\"$year/kotlin\"\e[38;5;255m,\e[0m\e[32m\` to the gradle base settings\e[0m"
      needs_action=1
    fi

    if create_file "$mainFile" "$(cat <<MAIN
package $package

import nl.sbmf21.aoc.common.AocBase

class Aoc : AocBase("$year")

fun main(args: Array<String>) = Aoc().run { exec(args) }
MAIN
    )"; then
      truncate -s -1 "$mainFile"
      echo -e "\e[32mHEY: Remember to add run configuration for Aoc.kt in InteliJ\e[0m"
      needs_action=1
    fi

    if (( needs_action )); then echo "Exiting because an action is required"; exit 0; fi

    if create_file "$solutionFile" "$(cat <<SOLUTION
package $package.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.TODO

class Day$day : Day() {

    override fun part1(): Any {
        return TODO
    }

    override fun part2(): Any {
        return TODO
    }
}
SOLUTION
    )"; then
      truncate -s -1 "$solutionFile"
      create_file "input/$year/input/day${day}.txt"
    fi

    if create_file "$testFile" "$(cat <<TEST
package $package.days

import nl.sbmf21.aoc.common.TODO
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day${day}Test {

    @Test
    fun testInput() = testDay(Day${day}::class.java, TODO, TODO)

    @Test
    fun testExample() = testDay(Day${day}::class.java, TODO, TODO, true)
}
TEST
    )"; then
      truncate -s -1 "$testFile"
      create_file "input/$year/example/day${day}.txt"
    fi

    run_gradle "kotlin" "$year" "$day"
    ;;





  "java")
    echo "Generating java files for $year:$day"
    folder=$(init_folder "$year" "java")
    packageFolder="$sourceBase/aoc${year:(-2)}"
    package="$packageBase.aoc${year:(-2)}"

    projectFile="$folder/build.gradle.kts"
    mainFile="$folder/src/main/java/$packageFolder/Aoc.java"
    solutionFile="$folder/src/main/java/$packageFolder/days/Day${day}.java"
    testFile="$folder/src/test/java/$packageFolder/days/Day${day}Test.java"

    needs_action=0

    if create_gradle_ci "$year" "java" "$folder"; then needs_action=1; fi

    if create_file "$projectFile" "$(cat <<GRADLE
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
}

val annotationsVersion: String by rootProject.extra
val sbmfMathVersion: String by rootProject.extra
val junitJupiterVersion: String by rootProject.extra

dependencies {
    implementation(project(":aoc-commons"))
    implementation("org.jetbrains:annotations:\$annotationsVersion")
    implementation("nl.sbmf21:math:\$sbmfMathVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:\$junitJupiterVersion")
    testImplementation(project(":aoc-test-utils"))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:\$junitJupiterVersion")
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
    mainClass = "$packageBase.aoc${year:(-2)}.Aoc"
}
GRADLE
    )"; then
      truncate -s -1 "$projectFile"
      echo -e "\e[32mHEY: Remember to add \`\e[48;5;0m\"$year\" \e[34mto \e[32m\"$year/java\"\e[38;5;255m,\e[0m\e[32m\` to the gradle base settings\e[0m"
      needs_action=1
    fi

    if create_file "$mainFile" "$(cat <<MAIN
package $package;

import nl.sbmf21.aoc.common.AocBase;

import java.util.HashMap;

public class Aoc extends AocBase {

    public Aoc() {
        super("$year", new HashMap<>());
    }

    public static void main(String[] args) {
        var aoc = new Aoc();

        aoc.exec(args);
    }
}
MAIN
    )"; then
      truncate -s -1 "$mainFile"
      echo -e "\e[32mHEY: Remember to add run configuration for Aoc.java in InteliJ\e[0m"
      needs_action=1
    fi

    if (( needs_action )); then echo "Exiting because an action is required"; exit 0; fi

    if create_file "$solutionFile" "$(cat <<SOLUTION
package $package.days;

import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import static nl.sbmf21.aoc.common.UtilKt.getTODO;

public class Day$day extends Day {

    @Override
    public @NotNull Object part1() {
        return getTODO();
    }

    @Override
    public @NotNull Object part2() {
        return getTODO();
    }
}
SOLUTION
    )"; then
      create_file "input/$year/input/day${day}.txt"
    fi

    if create_file "$testFile" "$(cat <<TEST
package $package.days;

import org.junit.jupiter.api.Test;

import static nl.sbmf21.aoc.common.UtilKt.getTODO;
import static nl.sbmf21.aoc.testing.UtilKt.testDay;

public class Day${day}Test {

    @Test
    public void testInput() {
        testDay(Day${day}.class, getTODO(), getTODO(), false, null);
    }

    @Test
    public void testExample() {
        testDay(Day${day}.class, getTODO(), getTODO(), true, null);
    }
}
TEST
    )"; then
      create_file "input/$year/example/day${day}.txt"
    fi

    run_gradle "java" "$year" "$day"
    ;;





  *)
    echo -e "\e[31mError: no handler to generate files for $language" >&2; exit 1
    ;;
esac

