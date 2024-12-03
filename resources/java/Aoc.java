package $package;

import nl.sbmf21.aoc.common.AocBase;

import java.util.HashMap;

public class Aoc extends AocBase {

    public Aoc() {
        super("$year", new HashMap<>());
    }

    public static void main(String[] args) {
        var aoc = new Aoc();

        aoc.exec(args);
    }
}
