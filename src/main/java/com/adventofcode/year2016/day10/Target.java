package com.adventofcode.year2016.day10;

import java.util.Objects;

class Target {
    public final ChipHolder type;
    public final HolderId id;

    Target(ChipHolder type, HolderId id) {
        this.type = Objects.requireNonNull(type);
        this.id = Objects.requireNonNull(id);
    }

    public static Target create(String target, String targetId) {
        return new Target(target.equals("bot") ? ChipHolder.BOT : ChipHolder.OUTPUT, HolderId.valueOf(targetId));
    }

    @Override
    public String toString() {
        return "Target{" +
                "type=" + type +
                ", id=" + id +
                '}';
    }
}