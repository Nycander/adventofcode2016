package com.adventofcode.year2016.day11;

enum Floor {
    FIRST,
    SECOND,
    THIRD,
    FOURTH;

    public boolean canMoveDown() {
        return this != Floor.FIRST;
    }

    public boolean canMoveUp() {
        return this != FOURTH;
    }

    public Floor moveUp() {
        return Floor.values()[this.ordinal() + 1];
    }

    public Floor moveDown() {
        return Floor.values()[this.ordinal() - 1];
    }
}