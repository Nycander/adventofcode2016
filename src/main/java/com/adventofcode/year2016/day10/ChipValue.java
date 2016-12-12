package com.adventofcode.year2016.day10;

import java.util.Objects;

class ChipValue {
    private final int value;

    ChipValue(int value) {
        this.value = value;
    }

    public static ChipValue valueOf(int value) {
        return new ChipValue(value);
    }

    public static ChipValue valueOf(String value) {
        return new ChipValue(Integer.parseInt(value));
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChipValue chipValue = (ChipValue) o;
        return value == chipValue.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}