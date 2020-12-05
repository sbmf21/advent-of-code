package io.frutsel_.aoc;

import java.util.ArrayList;

public abstract class ADay {

    protected final Aoc aoc;
    protected final ArrayList<String> input;

    protected ADay(Aoc aoc) {
        this.aoc = aoc;
        this.input = aoc.file(this);
    }

    public abstract int number();

    public abstract int part1();

    public abstract int part2();
}
