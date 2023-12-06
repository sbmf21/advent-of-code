package nl.sbmf21.aoc15.days;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static nl.sbmf21.aoc.testing.UtilKt.buildWithInput;
import static nl.sbmf21.aoc.testing.UtilKt.testDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    @Test
    public void testInput() {
        testDay(Day10.class, 492982, 6989950, false, null);
    }

    @Test
    public void testExample() {
        Day10 day = buildWithInput(Day10.class, new ArrayList<>());

        new HashMap<String, String>() {{
            put("1", "11");
            put("11", "21");
            put("21", "1211");
            put("1211", "111221");
            put("111221", "312211");
        }}.forEach((line, expected) -> assertEquals(expected, day.lookAndSay(line)));
    }
}
