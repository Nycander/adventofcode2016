package com.adventofcode.year2016.day10;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InstructionParser {
    private static Pattern initialBot = Pattern.compile("^value (?<value>\\d+) goes to bot (?<botId>\\d+)$");
    private static Pattern botGives = Pattern.compile("^bot (?<botId>\\d+) gives low to (?<lowTarget>\\w+) (?<lowTargetId>\\d+) and high to (?<highTarget>\\w+) (?<highTargetId>\\d+)$");

    private final InstructionVisitor instructionVisitor;

    InstructionParser(InstructionVisitor instructionVisitor) {
        this.instructionVisitor = Objects.requireNonNull(instructionVisitor);
    }

    public void parseLine(String line) {
        Matcher initialBotMatcher = initialBot.matcher(line);
        if (initialBotMatcher.matches()) {
            instructionVisitor.visit(InitialLoadInstruction.create(initialBotMatcher.group("botId"), initialBotMatcher.group("value")));
        }

        Matcher botGivesMatcher = botGives.matcher(line);
        if (botGivesMatcher.matches()) {
            instructionVisitor.visit(PassingInstruction.create(botGivesMatcher.group("botId"),
                    botGivesMatcher.group("lowTarget"), botGivesMatcher.group("lowTargetId"),
                    botGivesMatcher.group("highTarget"), botGivesMatcher.group("highTargetId")));
        }
    }
}