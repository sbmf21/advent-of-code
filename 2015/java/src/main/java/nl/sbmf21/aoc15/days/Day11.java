package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 extends Day {

    private final List<Character> illegals = List.of('i', 'o', 'l');
    private final List<String> increments = new ArrayList<>() {{
        for (char c = 'a'; c < 'a' + 24; c++) {
            Character[] chars = {c, (char) (c + 1), (char) (c + 2)};
            if (Stream.of(chars).anyMatch(illegals::contains)) continue;
            add(Arrays.stream(chars).map(String::valueOf).collect(Collectors.joining("")));
        }
    }};
    private final List<String> doubles = new ArrayList<>() {{
        for (char c = 'a'; c < 'a' + 26; c++) {
            if (illegals.contains(c)) continue;
            add(String.valueOf(c).repeat(2));
        }
    }};

    @Override
    public @NotNull Object part1() {
        String password = getInput().get(0);
        do {
            password = next(password);
        } while (isInvalid(password));
        return password;
    }

    @Override
    public @NotNull Object part2() {
        return nextValid(part1().toString());
    }

    public String nextValid(String password) {
        do password = next(password);
        while (isInvalid(password));
        return password;
    }

    private boolean isInvalid(String password) {
        return increments.stream().noneMatch(password::contains)
            || illegals.stream().anyMatch((c) -> password.indexOf(c) != -1)
            || doubles.stream().filter(password::contains).count() < 2;
    }

    private String next(String input) {
        StringBuilder builder = new StringBuilder(input);
        for (int i = input.length() - 1; i >= 0; i--) {
            char next = next(input.charAt(i));
            builder.setCharAt(i, next);
            if (next != 'a') break;
        }
        return builder.toString();
    }

    private char next(char input) {
        char next = (char) (input + 1);
        if (illegals.contains(next)) return next(next);
        if (next == (char) ('z' + 1)) return 'a';
        return next;
    }
}
