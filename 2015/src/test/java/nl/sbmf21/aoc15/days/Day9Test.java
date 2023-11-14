package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc15.AocTest;
import org.junit.jupiter.api.Test;

public class Day9Test extends AocTest {

    @Test
    public void testInput() {
        testDay(Day9.class, 251, 898);
    }

    @Test
    public void testExample() {
        testDay(Day9.class, 605, 982, true);
    }
}
