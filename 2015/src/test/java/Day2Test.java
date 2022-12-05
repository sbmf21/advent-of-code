import nl.sbmf21.aoc15.days.Day2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test extends AocTest {

    @Test
    public void testInput() {
        testDay(Day2.class, 1_586_300, 3_737_498);
    }

    @Test
    public void testSmallest() {
        var day = buildDay(Day2.class);

        assertEquals(10, day.smallest(new int[]{10, 20, 42, 12, 14}));
        assertEquals(2, day.smallest(new int[]{10, 2, 5, 14, 23}));
        assertEquals(3, day.smallest(new int[]{14, 3, 235, 19, 22}));
        assertEquals(123, day.smallest(new int[]{12813, 123, 4441, 241243}));
    }
}
