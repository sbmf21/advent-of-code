package nl.sbmf21.aoc15.days;

import org.junit.jupiter.api.Test;

import static nl.sbmf21.aoc.testing.UtilKt.testDay;

public class Day14Test {

    @Test
    public void testInput() {
        Day14.REINDEER_SCORE_TIME = Day14.DEFAULT_REINDEER_SCORE_TIME;
        testDay(Day14.class, 2640, 1102, false, null);
    }

    @Test
    public void testExample() {
        Day14.REINDEER_SCORE_TIME = 1000;
        testDay(Day14.class, 1120, 689, true, null);
    }
}
