package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Day10 extends Day {

    @Override
    public @NotNull Object part1() {
        return run(40);
    }

    @Override
    public @NotNull Object part2() {
        return run(50);
    }

    private int run(int rounds) {
        String line = getInput().get(0);
        for (int i = 0; i < rounds; i++) line = lookAndSay(line);
        return line.length();
    }

    public String lookAndSay(String input) {
        StringBuilder output = new StringBuilder();
        Character current = null;
        int count = 0;

        for (char c : input.toCharArray())
            if (Objects.equals(c, current)) count++;
            else {
                if (current != null) output.append(count).append(current);
                current = c;
                count = 1;
            }

        output.append(count).append(current);
        return output.toString();
    }
}
