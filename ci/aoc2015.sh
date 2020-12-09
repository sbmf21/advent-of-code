#!/bin/bash
if [ -d aoc-commons/.m2/repository/io/frutsel_/adventofcode.common ]; then
    if [ -d 2015/.m2/repository/io/frutsel_/adventofcode.common ]; then
        echo Removing existing commons lib
        rm -rf 2015/.m2/repository/io/frutsel_/adventofcode.common
    fi

    echo Copying commons from artifacts
    mv aoc-commons/.m2/repository/io/frutsel_/adventofcode.common 2015/.m2/repository/io/frutsel_/
fi
