package com.adventofcode.year2016.day5;

import java.util.Objects;

final class CharacterAtPosition {
    public final byte position;
    public final char character;

    CharacterAtPosition(byte position, char character) {
        this.position = position;
        this.character = character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterAtPosition that = (CharacterAtPosition) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}