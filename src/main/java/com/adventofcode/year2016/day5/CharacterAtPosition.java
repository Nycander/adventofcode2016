package com.adventofcode.year2016.day5;

import java.util.Objects;

final class CharacterAtPosition {
    public final byte position;
    public final char character;

    CharacterAtPosition(byte position, char character) {
        this.position = position;
        this.character = character;
    }

    public static CharacterAtPosition extractCharacterAndPosition(String md5) {
        // the sixth character represents the position (0-7),
        // and the seventh character is the character to put in that position.
        char position = md5.charAt(5);
        char character = md5.charAt(6);
        return new CharacterAtPosition(Byte.parseByte(String.valueOf(position)), character);
    }

    public static boolean hasValidPosition(String md5) {
        final char position = md5.charAt(5);
        return position >= '0' && position <= '7';
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