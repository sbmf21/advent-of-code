package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc15.AocTest;
import org.junit.jupiter.api.Test;

public class Day14Test extends AocTest {

    @Test
    public void testInput() {
        Day14.REINDEER_SCORE_TIME = Day14.DEFAULT_REINDEER_SCORE_TIME;
        testDay(Day14.class, 2640, 1102);
    }

    @Test
    public void testExample() {
        Day14.REINDEER_SCORE_TIME = 1000;
        testDay(Day14.class, 1120, 689, true);
    }
}
