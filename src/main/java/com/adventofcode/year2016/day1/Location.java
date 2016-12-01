package com.adventofcode.year2016.day1;

import java.util.Objects;

final class Location {
    public static final Location ORIGO = new Location(0, 0);
    public final int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getLength() {
        return Math.abs(x) + Math.abs(y);
    }

    public Location asMovedBy(int dx, int dy) {
        return new Location(x + dx, y + dy);
    }

    public Location asMovedBy(Direction direction) {
        return asMovedBy(direction.dx, direction.dy);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}