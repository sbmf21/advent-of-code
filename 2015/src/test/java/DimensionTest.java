import io.frutsel_.aoc.day2.Dimension;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DimensionTest {

    private Dimension dimension1;
    private Dimension dimension2;

    @Before
    public void setupDimensions() {
        dimension1 = Dimension.fromLine("2x3x4");
        dimension2 = Dimension.fromLine("1x1x10");
    }

    @Test
    public void testDimensionFromLine() {
        assertEquals(2, dimension1.l);
        assertEquals(3, dimension1.w);
        assertEquals(4, dimension1.h);

        assertEquals(1, dimension2.l);
        assertEquals(1, dimension2.w);
        assertEquals(10, dimension2.h);
    }

    @Test
    public void testSides() {
        var sides = dimension1.sides();

        assertEquals(3, sides.length);
        assertEquals(6, sides[0]);
        assertEquals(12, sides[1]);
        assertEquals(8, sides[2]);

        sides = dimension2.sides();

        assertEquals(3, sides.length);
        assertEquals(1, sides[0]);
        assertEquals(10, sides[1]);
        assertEquals(10, sides[2]);
    }

    @Test
    public void testPerimeters() {
        var perimeters = dimension1.perimeters();

        assertEquals(3, perimeters.length);
        assertEquals(10, perimeters[0]);
        assertEquals(14, perimeters[1]);
        assertEquals(12, perimeters[2]);

        perimeters = dimension2.perimeters();

        assertEquals(3, perimeters.length);
        assertEquals(4, perimeters[0]);
        assertEquals(22, perimeters[1]);
        assertEquals(22, perimeters[2]);
    }

    @Test
    public void testVolume() {
        assertEquals(24, dimension1.volume());
        assertEquals(10, dimension2.volume());
    }

    @Test
    public void testSize() {
        assertEquals(52, dimension1.size());
        assertEquals(42, dimension2.size());
    }
}
