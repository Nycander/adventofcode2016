package com.adventofcode.year2016.day1;

enum Direction {
    NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

    public final int dy;
    public final int dx;

    Direction(int dx, int dy) {
        this.dy = dy;
        this.dx = dx;
    }

    public Direction turn(Turn turn) {
        boolean clockwise = turn == Turn.RIGHT;
        int ordinalDirection = clockwise ? 1 : -1;
        int i = (this.ordinal() + ordinalDirection);
        return Direction.values()[i < 0 ? i + 4 : i % 4];
    }
}