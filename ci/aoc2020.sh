#!/bin/bash
if [ -d /aoc-commons/.m2/repository/io/frutsel_/adventofcode.common ]; then
    if [ -d /2020/kotlin/.m2/repository/io/frutsel_/adventofcode.common ]; then
        rm -rf /2020/kotlin/.m2/repository/io/frutsel_/adventofcode.common
    fi

    mv aoc-commons/.m2/repository/io/frutsel_/adventofcode.common 2020/kotlin/.m2/repository/io/frutsel_/
fi
