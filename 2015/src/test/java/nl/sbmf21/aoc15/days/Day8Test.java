package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc15.AocTest;
import org.junit.jupiter.api.Test;

public class Day8Test extends AocTest {

    @Test
    public void testInput() {
        testDay(Day8.class, 1342, 2074);
    }

    @Test
    public void testExample() {
        testDay(Day8.class, 12, 19, true);
    }
}
