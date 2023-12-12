package nl.sbmf21.aoc15.days;

import org.junit.jupiter.api.Test;

import static nl.sbmf21.aoc.testing.UtilKt.testDay;

public class Day9Test {

    @Test
    public void testInput() {
        testDay(Day9.class, 251, 898, false, null);
    }

    @Test
    public void testExample() {
        testDay(Day9.class, 605, 982, true, null);
    }
}
