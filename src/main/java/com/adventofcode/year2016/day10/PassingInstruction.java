package com.adventofcode.year2016.day10;

import java.util.Objects;

class PassingInstruction {
    public final HolderId botId;
    public final Target lowTarget;
    public final Target highTarget;

    PassingInstruction(HolderId botId, Target lowTarget, Target highTarget) {
        this.botId = Objects.requireNonNull(botId);
        this.lowTarget = Objects.requireNonNull(lowTarget);
        this.highTarget = Objects.requireNonNull(highTarget);
    }

    public static PassingInstruction create(String botId, String lowTarget, String lowTargetId, String highTarget, String highTargetId) {
        return new PassingInstruction(HolderId.valueOf(botId), Target.create(lowTarget, lowTargetId), Target.create(highTarget, highTargetId));
    }

    @Override
    public String toString() {
        return "PassingInstruction{" +
                "botId=" + botId +
                ", lowTarget=" + lowTarget +
                ", highTarget=" + highTarget +
                '}';
    }
}
