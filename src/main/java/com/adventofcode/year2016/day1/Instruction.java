package com.adventofcode.year2016.day1;

import java.util.Objects;

final class Instruction {
    private final Turn turn;
    private final int length;

    public Instruction(Turn turn, int length) {
        this.turn = Objects.requireNonNull(turn);
        if (length <= 0) {
            throw new IllegalArgumentException("length must be positive");
        }
        this.length = length;
    }

    public static Instruction valueOf(String potentialInstruction) {
        final Turn turn;
        switch (potentialInstruction.charAt(0)) {
            case 'R':
                turn = Turn.RIGHT;
                break;
            case 'L':
                turn = Turn.LEFT;
                break;
            default:
                throw new IllegalArgumentException("First letter in instruction is neither R or L, instruction=" + potentialInstruction);
        }

        int length = Integer.valueOf(potentialInstruction.substring(1));
        return new Instruction(turn, length);
    }

    public Turn getTurn() {
        return turn;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return length == that.length &&
                turn == that.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn, length);
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "turn=" + turn +
                ", length=" + length +
                '}';
    }
}