package io.frutsel_.aoc.days;

import io.frutsel_.aoc.ADay;
import io.frutsel_.aoc.Aoc;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class Day4 extends ADay {

    private Map<String, Integer> hashes = null;
    private final MessageDigest digester;
    private final String key;
    private final int max;

    public Day4(Aoc aoc) throws Exception {
        super(aoc);

        var input = input();
        digester = MessageDigest.getInstance("MD5");
        key = input.get(0);
        max = Integer.parseInt(input.get(1));
    }

    @Override
    public int number() {
        return 4;
    }

    @Override
    public int part1() {
        try {
            return aoc.cache(this, cacheKey(1), () -> findLowest(5));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int part2() {
        try {
            return aoc.cache(this, cacheKey(2), () -> findLowest(6));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private String cacheKey(int part) {
        return String.format("part%d-%s-%d", part, key, max);
    }

    private int findLowest(int length) {
        var lowest = max;

        this.loadHashes();

        for (String hash : hashes.keySet()) {
            if (hash.startsWith("0".repeat(length))) {
                var i = hashes.get(hash);

                if (i < lowest) {
                    lowest = i;
                }
            }
        }
        return lowest;
    }

    private void loadHashes() {
        if (hashes != null) {
            return;
        }

        var start = System.currentTimeMillis();
        hashes = this.buildHashCache();
        var end = System.currentTimeMillis();

        System.out.println(String.format("Generating hashes took %s seconds", (end - start) / 1000));
    }

    private HashMap<String, Integer> buildHashCache() {
        var map = new HashMap<String, Integer>();

        for (var i = 0; i < max; i++) {
            map.put(toHashString(i), i);
        }

        return map;
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
