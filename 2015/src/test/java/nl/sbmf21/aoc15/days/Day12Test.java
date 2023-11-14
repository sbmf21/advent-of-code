package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc15.AocTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test extends AocTest {

    @Test
    public void testInput() {
        testDay(Day12.class, 191164, 87842);
    }

    @Test
    public void testExamplePart1() {
        new HashMap<String, Integer>() {{
            put("[1,2,3]", 6);
            put("{\"a\":2,\"b\":4}", 6);
            put("[[[3]]]", 3);
            put("{\"a\":{\"b\":4},\"c\":-1}", 3);
            put("{\"a\":[-1,1]}", 0);
            put("[-1,{\"a\":1}]", 0);
            put("[]", 0);
            put("{}", 0);
        }}.forEach((input, expected) -> {
            Day12 day = buildDay(Day12.class, List.of(input));
            assertEquals(expected, day.part1());
        });
    }

    @Test
    public void testExamplePart2() {
        new HashMap<String, Integer>() {{
            put("[1,2,3]", 6);
            put("[1,{\"c\":\"red\",\"b\":2},3]", 4);
            put("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}", 0);
            put("[1,\"red\",5]", 6);
        }}.forEach((input, expected) -> {
            Day12 day = buildDay(Day12.class, List.of(input));
            assertEquals(expected, day.part2());
        });
    }
}
