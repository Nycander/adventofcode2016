package com.adventofcode.year2016.day10;

import java.util.Objects;
import java.util.Optional;

class BotState {
    private final HolderId id;

    private ChipValue low;
    private ChipValue high;

    BotState(HolderId id) {
        this.id = Objects.requireNonNull(id);
    }

    public void addValue(ChipValue value) {
        if (hasValues()) {
            return;
        }

        if (!low().isPresent() && !high().isPresent()) {
            low = value;
            return;
        }

        if (low().isPresent()) {
            if (value.getValue() < low.getValue()) {
                high = low;
                low = value;
            }
            if (value.getValue() > low.getValue()) {
                high = value;
            }
            return;
        }

        if (high().isPresent()) {
            if (value.getValue() > high.getValue()) {
                low = high;
                high = value;
            }
            if (value.getValue() < high.getValue()) {
                low = value;
            }
            return;
        }

        throw new IllegalStateException("high=" + high + ", low=" + low + ", incoming=" + value);
    }

    public Optional<ChipValue> low() {
        return Optional.ofNullable(low);
    }

    public Optional<ChipValue> high() {
        return Optional.ofNullable(high);
    }

    public HolderId getId() {
        return id;
    }

    public boolean hasValues() {
        return low().isPresent() && high().isPresent();
    }


    @Override
    public String toString() {
        return "BotState{" +
                "id=" + id +
                ", low=" + low +
                ", high=" + high +
                '}';
    }
}