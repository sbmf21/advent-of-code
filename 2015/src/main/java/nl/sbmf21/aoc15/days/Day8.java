package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Day8 extends Day {

    private final int inputLength;
    private final int escapedLength;
    private final int encodedLength;

    public Day8() {
        List<StringData> data = getInput().stream().map(StringData::new).toList();
        inputLength = data.stream().map((d) -> d.inputLength).reduce(0, Integer::sum);
        escapedLength = data.stream().map((d) -> d.escapedLength).reduce(0, Integer::sum);
        encodedLength = data.stream().map((d) -> d.encodedLength).reduce(0, Integer::sum);
    }

    @Override
    public @NotNull Object part1() {
        return inputLength - escapedLength;
    }

    @Override
    public @NotNull Object part2() {
        return encodedLength - inputLength;
    }

    private static class StringData {

        private final int inputLength;
        private final int escapedLength;
        private final int encodedLength;

        public StringData(String string) {
            inputLength = string.length();
            escapedLength = escape(string).length();
            encodedLength = unescape(string).length();
        }

        private String unescape(String string) {
            string = string.replaceAll("\\\\{2}", "****");
            string = string.replaceAll("\\\\\"", "****");
            string = string.replaceAll("\\\\x[0-9a-f]{2}", "*****");
            string = string.replaceAll("\"", "***");
            return string;
        }

        private String escape(String string) {
            string = string.replaceAll("\\\\{2}", "*");
            string = string.replaceAll("\\\\\"", "*");
            string = string.replaceAll("\\\\x[0-9a-f]{2}", "*");
            string = string.replaceAll("\"", "");
            return string;
        }
    }
}
