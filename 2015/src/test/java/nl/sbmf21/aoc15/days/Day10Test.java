package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc15.AocTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test extends AocTest {

    @Test
    public void testInput() {
        testDay(Day10.class, 492982, 6989950);
    }

    @Test
    public void testExample() {
        Day10 day = buildDay(Day10.class, new ArrayList<>());
        new HashMap<String, String>() {{
            put("1", "11");
            put("11", "21");
            put("21", "1211");
            put("1211", "111221");
            put("111221", "312211");
        }}.forEach((line, expected) -> assertEquals(expected, day.lookAndSay(line)));
    }
}
