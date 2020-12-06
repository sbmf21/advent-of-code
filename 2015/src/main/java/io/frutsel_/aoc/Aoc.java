package io.frutsel_.aoc;

import io.frutsel_.aoc.common.AocBase;

public class Aoc extends AocBase {

    public Aoc() {
        super("2015");
    }

    public static void main(String[] args) {
        var aoc = new Aoc();

        aoc.init(args);
        aoc.runDays();
        aoc.report();
    }
}
