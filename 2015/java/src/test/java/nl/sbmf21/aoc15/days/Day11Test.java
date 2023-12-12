package nl.sbmf21.aoc15.days;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static nl.sbmf21.aoc.testing.UtilKt.buildWithInput;
import static nl.sbmf21.aoc.testing.UtilKt.testDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {

    @Test
    public void testInput() {
        testDay(Day11.class, "cqjxxyzz", "cqkaabcc", false, null);
    }

    @Test
    public void testExample() {
        Day11 day = buildWithInput(Day11.class, new ArrayList<>());

        new HashMap<String, String>() {{
            put("abcdefgh", "abcdffaa");
            put("ghijklmn", "ghjaabcc");
        }}.forEach((input, expected) -> assertEquals(expected, day.nextValid(input)));
    }
}
