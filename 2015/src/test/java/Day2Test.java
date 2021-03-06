import nl.sbmf21.aoc15.Aoc;
import nl.sbmf21.aoc15.days.Day2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {

    private final Day2 day = new Day2(new Aoc(), 2);

    @Test
    public void testPart1Output() {
        assertEquals(1586300, (int) day.part1());
    }

    @Test
    public void testPart2Output() {
        assertEquals(3737498, (int) day.part2());
    }

    @Test
    public void testSmallest() {
        assertEquals(10, day.smallest(new int[]{10, 20, 42, 12, 14}));
        assertEquals(2, day.smallest(new int[]{10, 2, 5, 14, 23}));
        assertEquals(3, day.smallest(new int[]{14, 3, 235, 19, 22}));
        assertEquals(123, day.smallest(new int[]{12813, 123, 4441, 241243}));
    }
}
