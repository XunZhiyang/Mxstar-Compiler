package IR;

import IR.Instruction.BranchInst;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInst;
import Symbol.GlobalScope;

import java.util.ArrayList;
import java.util.List;

public class BasicBlock extends User {
    private List<Instruction> instructionList = new ArrayList<>();
    private boolean terminated = false;

    public BasicBlock(String label) {
        super(label, GlobalScope.getVoidType());
    }

    public void addInst(Instruction instruction) {
        if(!terminated) {
            instructionList.add(instruction);
            instruction.setFromBlock(this);
            this.terminated = instruction.isTerminator();
        }
    }

    public void addFront(Instruction instruction) {
        instructionList.add(0, instruction);
        instruction.setFromBlock(this);
    }

    public boolean isTerminated() {
        return terminated;
    }

    public List<Instruction> getInstructionList() {
        return instructionList;
    }

    public void removeInstruction(Instruction instruction) {
        instructionList.remove(instruction);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    public List<BasicBlock> getSuccessors() {
        List<BasicBlock> successors = new ArrayList<>();
        Instruction instruction = instructionList.get(instructionList.size() - 1);
        if (instruction instanceof JumpInst) {
            successors.add((BasicBlock) instruction.getOperand(0));
        }
        if (instruction instanceof BranchInst) {
            successors.add((BasicBlock) instruction.getOperand(1));
            successors.add((BasicBlock) instruction.getOperand(2));
        }
        return successors;
    }
}
