package io.frutsel_.aoc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class ADay {

    public abstract APart<?>[] parts();

    public abstract int dayNumber();

    protected BufferedReader getResource() {
        var fileName = String.format("/input/%s.txt", this.getClass().getSimpleName().toLowerCase());
        var inputStream = AdventOfCode.class.getResourceAsStream(fileName);

        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
