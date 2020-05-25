package OperandRV;

import IR.BasicBlock;
import OperandRV.InstRV.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockRV extends OperandRV {
    private List<InstRV> instructions = new ArrayList<>();

    private List<BlockRV> predecessors = new ArrayList<>();
    private List<BlockRV> successors = new ArrayList<>();

    private Set<Register> liveIn = new HashSet<>();
    private Set<Register> liveOut = new HashSet<>();

    public BlockRV(BasicBlock block) {
        this.identifier = block.getIdentifier();
        block.setCorRV(this);
    }

    public void add(InstRV instruction) {
        instructions.add(instruction);
    }

    public List<InstRV> getInstructions() {
        return instructions;
    }

    public void addPredecessor(BlockRV predecessor) {
        predecessors.add(predecessor);
    }

    public void addSuccessor(BlockRV successor) {
        successors.add(successor);
    }

    public List<BlockRV> getPredecessors() {
        return predecessors;
    }

    public List<BlockRV> getSuccessors() {
        return successors;
    }

    public Set<Register> getLiveIn() {
        return liveIn;
    }

    public Set<Register> getLiveOut() {
        return liveOut;
    }
}
