package com.adventofcode.year2016.day10;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class BotInspector implements InstructionVisitor {
    private final Map<ChipValue, HolderId> inits = new HashMap<>();
    private final Map<HolderId, PassingInstruction> passes = new HashMap<>();

    private final Map<HolderId, BotState> botStates = new ConcurrentHashMap<>();
    private final Map<HolderId, ChipValue> outputs = new ConcurrentHashMap<>();

    @Override
    public void visit(InitialLoadInstruction initialLoadInstruction) {
        HolderId bot = initialLoadInstruction.bot;
        ChipValue value = initialLoadInstruction.value;
        inits.put(value, bot);
    }

    private void setValueForBotAndPassValues(HolderId bot, ChipValue value) {
        BotState botState = botStates.getOrDefault(bot, new BotState(bot));
        if (botState.hasValues()) {
            return;
        }
        botState.addValue(value);
        botStates.put(botState.getId(), botState);
        if (botState.hasValues()) {
            passValues(botState);
        }
    }

    private void passValues(BotState botState) {
        PassingInstruction passingInstruction = passes.get(botState.getId());
        if (passingInstruction.lowTarget.type == ChipHolder.BOT) {
            setValueForBotAndPassValues(passingInstruction.lowTarget.id, botState.low().get());
        } else {
            outputs.putIfAbsent(passingInstruction.lowTarget.id, botState.low().get());
        }
        if (passingInstruction.highTarget.type == ChipHolder.BOT) {
            setValueForBotAndPassValues(passingInstruction.highTarget.id, botState.high().get());
        } else {
            outputs.putIfAbsent(passingInstruction.highTarget.id, botState.high().get());
        }
    }

    @Override
    public void visit(PassingInstruction passInstruction) {
        passes.put(passInstruction.botId, passInstruction);
    }

    public Optional<HolderId> responsibleFor(ChipValue low, ChipValue high) {
        inits.entrySet().stream().forEach(e -> setValueForBotAndPassValues(e.getValue(), e.getKey()));
        return botStates.entrySet().stream()
                .filter(e -> e.getValue().low().isPresent())
                .filter(e -> e.getValue().low().get().getValue() == low.getValue())
                .filter(e -> e.getValue().high().isPresent())
                .filter(e -> e.getValue().high().get().getValue() == high.getValue())
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public ChipValue getOutput(HolderId id) {
        return outputs.get(id);
    }
}