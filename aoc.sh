#!/bin/bash

F_RED="\033[31m"
F_GREEN="\033[32m"
F_BLUE="\033[34m"
F_RESET="\033[0m"

function trunc {
  if [[ $1 == *.kt ]] || [[ $1 == *.kts ]]; then
    truncate -s -1 "$1"
  fi
}

function check_arguments {
  if [ "$#" -ne 3 ] && [ "$#" -ne 2 ]; then
    me=$(basename "$0")
    echo -e "${F_RED}Usage: ./$me year language [day]" >&2; exit 1
  fi

  errors=0
  yearRegex='^(20(1[5-9]|[2-9][0-9]))|(2[1-9][0-9]{2})$'
  dayRegex='^((2[0-5])|(1[0-9])|([1-9]))$'
  languages=("kotlin" "java")

  export year=$1
  export language=$2
  export day=$3

  # check year

  if ! [[ "$year" =~ $yearRegex ]] ; then
    echo -e "${F_RED}Error: '$year' is not a valid year" >&2; ((errors++))
  fi

  if (( "$year" >= 2024 )); then
    sourceBase="je/bouk"
    packageBase="je.bouk"
  else
    sourceBase="nl/sbmf21"
    packageBase="nl.sbmf21"
  fi

  # check language

  if [[ ! "${languages[*]}" =~ ${language} ]]; then
    echo -e "${F_RED}Error: '$language' is not a supported language" >&2; ((errors++))
  fi

  # check day

  if [[ ! "$day" =~ $dayRegex ]] && [ -n "$day" ]; then
    echo -e "${F_RED}Error: '$day' is not a valid day" >&2; ((errors++))
  fi

  # finish

  if [ $errors -gt 0 ]; then
    exit $errors
  fi
}

function base_folder {
  folder=$(dirname "$0")
  echo "$folder"
}

function init_folder {
  base=$(base_folder)
  folder="$base/$year/$language"
  mkdir -p "$folder"
  echo "$folder"
}

function create_file {
  if [ -s "$1" ]; then return 1; fi

  echo -e "Generating $F_BLUE$1$F_RESET"
  mkdir -p "${1%/*}" && touch "$1"

  if [ -z "$2" ]; then return 0; fi

  template="resources/$2"
  template="resources/$2"
  # shellcheck disable=SC2016
  # this is how envsubset knows what to export
  content=$(envsubst '$year,$day,$language,$package' < "$template")
  echo "$content" > "$1"
  trunc "$1"

  return 0
}

function add_to_list {
  IFS=$'\n'

  projects=$(awk '/start/{f=1; next} /end/{f=0} f' "$2") # read projects between start and end
  read -rd '' -a projects <<<"$projects" # convert to array
  projects+=("$1") # push file
  read -rd '' -a projects < <(for project in "${projects[@]}"; do echo "$project"; done | sort) # sort

  unset IFS

  out=""
  for c in "${!projects[@]}"; do
    if [ "$c" -gt 0 ]; then out+="\n"; fi
    out+="${projects[c]}"
  done

  content=$(awk -v projects="$out" '/end/{f=0} !f; sub(/start/,""){print projects; f=1}' "$2")
  echo "$content" > "$2"
  trunc "$2"

  unset projects
  unset content
  unset out
}

function get_input {
  file="$1"

  if [ ! -e "$file" ] || [ -s "$file" ]; then
    # file does not exist OR file has content
    return 0
  fi

  sessionFile="resources/.session"
  if [ ! -e "$sessionFile" ]; then
    mkdir -p "$(dirname "$sessionFile")"
    touch "$sessionFile"
  fi

  if [ ! -s "$sessionFile" ]; then
    echo -ne "${F_RED}Please paste your AOC session here${F_RESET}: "
    read -r input
    echo "$input" > "$sessionFile"
    truncate -s -1 "$sessionFile"
  fi

  echo -n "Collecting input"

  session=$(cat "$sessionFile")
  content=$(curl -s --fail \
     --cookie "session=$session" \
    "https://adventofcode.com/$year/day/$day/input")

  code=$?
  if [ $code -ne 0 ]; then
    echo -e ": ${F_RED}failed${F_RESET}."
    return
  fi

  echo "$content" > "$file"
  echo -e ": ${F_GREEN}done${F_RESET}."
  truncate -s -1 "$file"
}

function gradle_create_ci {
  if create_file "$1/.gitlab-ci.yml" ".gitlab-ci.gradle.yml"; then
    echo "Adding to main CI file"
    add_to_list "  - local: /$year/$language/.gitlab-ci.yml" ".gitlab-ci.yml"
  fi
}

function gradle_add_project {
  if create_file "$1" "$2"; then
    echo "Adding to main gradle project"
    add_to_list "    \"$year:$language\"," "settings.gradle.kts"
  fi
}

function gradle_run_year {
  cd "$(base_folder)" || exit 1

  if ! ./gradlew ":$year@$language:clean" ":$year@$language:test" ":$year@$language:jar"; then
    return 1 # no need to log nothing, gradle does that for us
  fi

  java -jar "$year/$language/build/libs/aoc$year-shaded.jar"
}

function gradle_run_single {
  cd "$(base_folder)" || exit 1

  get_input "$inputFile"

  if ! ./gradlew ":$year@$language:clean" ":$year@$language:test" --tests "$packageBase.aoc${year:(-2)}.days.Day${day}Test" ":$year@$language:jar"; then
    return 1 # no need to log nothing, gradle does that for us
  fi

  java -jar "$year/$language/build/libs/aoc$year-shaded.jar" --day="$day"
}

check_arguments "$@"

inputFile="$(base_folder)/input/$year/input/day${day}.txt"
exampleFile="$(base_folder)/input/$year/example/day${day}.txt"

case $language in
  "kotlin")
    folder=$(init_folder)
    packageFolder="$sourceBase/aoc${year:(-2)}"
    export package="$packageBase.aoc${year:(-2)}"

    gradle_create_ci "$folder"
    gradle_add_project "$folder/build.gradle.kts" "kotlin/build.gradle.kts"
    create_file "$folder/src/main/kotlin/$packageFolder/Aoc.kt" "kotlin/Aoc.kt"

    if [ -n "$day" ]; then
      if create_file "$folder/src/main/kotlin/$packageFolder/days/Day${day}.kt" "kotlin/Day.kt"; then create_file "$inputFile"; fi
      if create_file "$folder/src/test/kotlin/$packageFolder/days/Day${day}Test.kt" "kotlin/Test.kt"; then create_file "$exampleFile"; fi

      gradle_run_single
    else
      gradle_run_year
    fi
    ;;


  "java")
    folder=$(init_folder)
    packageFolder="$sourceBase/aoc${year:(-2)}"
    export package="$packageBase.aoc${year:(-2)}"

    gradle_create_ci "$folder"
    gradle_add_project "$folder/build.gradle.kts" "java/build.gradle.kts"
    create_file "$folder/src/main/java/$packageFolder/Aoc.java" "java/Aoc.java"

    if [ -n "$day" ]; then
      if create_file "$folder/src/main/java/$packageFolder/days/Day${day}.java" "java/Day.java"; then create_file "$inputFile"; fi
      if create_file "$folder/src/test/java/$packageFolder/days/Day${day}Test.java" "java/Test.java"; then create_file "$exampleFile"; fi

      gradle_run_single
    else
      gradle_run_year
    fi
    ;;


  *)
    echo -e "${F_RED}Error: no handler to generate files for $language" >&2; exit 1
    ;;
esac
