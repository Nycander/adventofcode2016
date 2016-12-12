package com.adventofcode.year2016.day10;

interface InstructionVisitor {
    void visit(InitialLoadInstruction initialLoadInstruction);

    void visit(PassingInstruction passInstruction);
}