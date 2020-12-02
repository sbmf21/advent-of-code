package io.frutsel_.aoc;

public abstract class APart<D extends IDay> {

    protected final D day;

    public APart(D day) {
        this.day = day;
    }

    public abstract void solve();

    public abstract int partNumber();
}
