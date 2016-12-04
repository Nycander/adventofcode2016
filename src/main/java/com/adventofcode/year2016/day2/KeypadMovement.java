package com.adventofcode.year2016.day2;

public enum KeypadMovement {
    UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

    public final int dx;
    public final int dy;

    KeypadMovement(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static KeypadMovement valueOf(char ch) {
        switch (ch) {
            case 'U':
                return UP;
            case 'R':
                return RIGHT;
            case 'D':
                return DOWN;
            case 'L':
                return LEFT;
        }
        throw new IllegalArgumentException();
    }
}