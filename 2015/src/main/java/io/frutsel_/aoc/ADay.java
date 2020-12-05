package io.frutsel_.aoc;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public abstract class ADay {

    protected final Aoc aoc;

    public abstract int number();

    public abstract int part1();

    public abstract int part2();

    protected ArrayList<String> input() {
        return aoc.file(this);
    }
}
