package io.frutsel_.aoc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class APart<D extends ADay> {

    protected final D day;

    public abstract String solve() throws Exception;

    public abstract int partNumber();
}
