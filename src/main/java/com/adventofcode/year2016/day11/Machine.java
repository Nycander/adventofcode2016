package com.adventofcode.year2016.day11;

import java.util.Objects;

abstract class Machine {
    private final String type;

    protected Machine(String type) {
        this.type = Objects.requireNonNull(type).intern();
    }

    @SuppressWarnings("StringEquality")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return type == machine.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), type);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

    public String getSymbol() {
        return type.substring(0, 1).toUpperCase() + getClass().getSimpleName().substring(0, 1);
    }
}
