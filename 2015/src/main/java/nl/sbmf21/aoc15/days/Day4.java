package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;

public class Day4 extends Day {

    private final HashMap<Integer, String> keys = new HashMap<>();
    private final MessageDigest digester;
    private final String key;
    private final int max;

    public Day4() throws Exception {
        digester = MessageDigest.getInstance("MD5");
        key = getInput().get(0);
        max = Integer.parseInt(getInput().get(1));
    }

    @Override
    public @NotNull Integer part1() {
        return findLowest(5);
    }

    @Override
    public @NotNull Integer part2() {
        return findLowest(6);
    }

    private int findLowest(int num) {
        for (int i = 0; i <= max; i++) {
            if (!keys.containsKey(i)) {
                keys.put(i, toHashString(i));
            }

            if (keys.get(i).startsWith("0".repeat(num))) {
                return i;
            }
        }
        return -1;
    }

    private String toHashString(int number) {
        var hash = new StringBuilder(hashInt(number).toString(16));

        hash.insert(0, "0".repeat(32 - hash.length()));

        return hash.toString();
    }

    private BigInteger hashInt(int number) {
        return new BigInteger(1, digest(number));
    }

    private byte[] digest(int number) {
        return digester.digest(String.format("%s%d", this.key, number).getBytes());
    }
}
