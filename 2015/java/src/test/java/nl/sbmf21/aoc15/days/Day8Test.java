package nl.sbmf21.aoc15.days;

import org.junit.jupiter.api.Test;

import static nl.sbmf21.aoc.testing.UtilKt.testDay;

public class Day8Test {

    @Test
    public void testInput() {
        testDay(Day8.class, 1342, 2074, false, null);
    }

    @Test
    public void testExample() {
        testDay(Day8.class, 12, 19, true, null);
    }
}
