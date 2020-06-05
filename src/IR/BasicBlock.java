package IR;

import IR.Instruction.BranchInst;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInst;
import OperandRV.BlockRV;
import Symbol.GlobalScope;

import java.util.ArrayList;
import java.util.List;

public class BasicBlock extends User {
    private List<Instruction> instructionList = new ArrayList<>();
    private boolean terminated = false;

    private BlockRV corRV;

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

//    public void strongAddInst(Instruction instruction) {
//        instruction.setFromBlock(this);
//        instructionList.add(instruction);
//    }

    public void addFront(Instruction instruction) {
        instructionList.add(0, instruction);
        instruction.setFromBlock(this);
    }

    public boolean isNotTerminated() {
        return !terminated;
    }

    public void cancelTermination() {
        terminated = false;
    }

    public List<Instruction> getInstructionList() {
        return instructionList;
    }

    public Instruction getLastInstruction() {
        return instructionList.get(instructionList.size() - 1);
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

    public void redirect(BasicBlock from, BasicBlock to) {
        Instruction instruction = instructionList.get(instructionList.size() - 1);
        if (instruction instanceof JumpInst) {
            assert instruction.getOperand(0) == from;
            instruction.setOperand(0, to);
        }
        if (instruction instanceof BranchInst) {
            if (instruction.getOperand(1) == from) {
                instruction.setOperand(1, to);
            }
            if (instruction.getOperand(2) == from) {
                instruction.setOperand(2, to);
            }
        }
//        if (flag) {
//            System.err.println(this.identifier + " " + instruction.getOperand(1) + " " +
//                    instruction.getOperand(2) + " " + from.identifier + " " + to.identifier);
//            throw new RuntimeException();
//        }
    }
}
