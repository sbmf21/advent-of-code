import nl.sbmf21.aoc15.Aoc;
import nl.sbmf21.aoc15.days.Day3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {

    private final Day3 day = new Day3(new Aoc(), 3);

    @Test
    public void testPart1Output() {
        assertEquals(2081, (int) day.part1());
    }

    @Test
    public void testPart2Output() {
        assertEquals(2341, (int) day.part2());
    }
}
