import nl.sbmf21.aoc15.Aoc;
import nl.sbmf21.aoc15.days.Day4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Test {

    private final Day4 day;

    public Day4Test() throws Exception {
        day = new Day4(new Aoc(), 4);
    }

    @Test
    public void testPart1Output() {
        assertEquals(117946, (int) day.part1());
    }

    @Test
    public void testPart2Output() {
        assertEquals(3938038, (int) day.part2());
    }
}
