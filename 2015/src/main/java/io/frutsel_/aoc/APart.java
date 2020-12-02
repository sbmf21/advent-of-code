package io.frutsel_.aoc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor public abstract class APart<D extends IDay> {

    protected final D day;

    public abstract void solve();

    public abstract int partNumber();
}
