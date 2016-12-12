package com.adventofcode.year2016.day10;

class InitialLoadInstruction {
    public final HolderId bot;
    public final ChipValue value;

    InitialLoadInstruction(HolderId bot, ChipValue value) {
        this.bot = bot;
        this.value = value;
    }

    public static InitialLoadInstruction create(String botId, String value) {
        return new InitialLoadInstruction(HolderId.valueOf(botId), ChipValue.valueOf(value));
    }

    @Override
    public String toString() {
        return "InitialLoadInstruction{" +
                "bot=" + bot +
                ", value=" + value +
                '}';
    }
}