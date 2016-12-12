package com.adventofcode.year2016.day10;

import java.util.Objects;

class HolderId {
    private final int id;

    HolderId(int id) {
        this.id = id;
    }

    public static HolderId valueOf(int id) {
        return new HolderId(id);
    }

    public static HolderId valueOf(String id) {
        return new HolderId(Integer.parseInt(id));
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolderId holderId = (HolderId) o;
        return id == holderId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}