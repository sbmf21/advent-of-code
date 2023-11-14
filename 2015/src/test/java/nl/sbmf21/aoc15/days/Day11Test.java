package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc15.AocTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test extends AocTest {

    @Test
    public void testInput() {
        testDay(Day11.class, "cqjxxyzz", "cqkaabcc");
    }

    @Test
    public void testExample() {
        Day11 day = buildDay(Day11.class, new ArrayList<>());
        new HashMap<String, String>() {{
            put("abcdefgh", "abcdffaa");
            put("ghijklmn", "ghjaabcc");
        }}.forEach((input, expected) -> assertEquals(expected, day.nextValid(input)));
    }
}
