import io.frutsel_.aoc.ADay;
import io.frutsel_.aoc.Aoc;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AocTest {

    private final Aoc aoc = new Aoc();

    @Test
    public void names() {
        for (ADay day : aoc.findDays()) {
            assertEquals(String.format("Day%s", day.number()), day.getClass().getSimpleName());
        }
    }
}
