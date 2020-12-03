import io.frutsel_.aoc.day2.Day2;
import io.frutsel_.aoc.day2.Part1;
import io.frutsel_.aoc.day2.Part2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {

    private Day2 day;

    @Before
    public void createDay() {
        day = new Day2();
    }

    @Test
    public void testDayNumber() {
        assertEquals(2, day.dayNumber());
    }

    @Test
    public void testPart1Output() throws IOException {
        var part = new Part1(day);

        assertEquals("1586300", part.solve());
        assertEquals(1, part.partNumber());
    }

    @Test
    public void testPart2Output() throws IOException {
        var part = new Part2(day);

        assertEquals("3737498", part.solve());
        assertEquals(2, part.partNumber());
    }

    @Test
    public void testNumber() {
        assertEquals(2, day.dayNumber());
    }

    @Test
    public void testParseDimensions() throws IOException {
        var dimensions = day.parseDimensions();

        assertEquals(1000, dimensions.size());
    }

    @Test
    public void testSmallest() {
        assertEquals(10, day.smallest(new int[]{10, 20, 42, 12, 14}));
        assertEquals(2, day.smallest(new int[]{10, 2, 5, 14, 23}));
        assertEquals(3, day.smallest(new int[]{14, 3, 235, 19, 22}));
        assertEquals(123, day.smallest(new int[]{12813, 123, 4441, 241243}));
    }
}
