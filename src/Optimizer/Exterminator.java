package Optimizer;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Instruction.*;
import IR.Module;
import IR.Value;

import java.util.List;

public class Exterminator extends FunctionOptimizer {
    private CFG cfg;
    private Function curFunction;

    public Exterminator(Module module) {
        super(module);
    }

    private boolean transfer(BasicBlock from, BasicBlock to) {
        List<BasicBlock> predecessors = cfg.getPredecessors(from);

        int phiCnt = 0;
        for (Instruction inst : to.getInstructionList()) {
            if (inst instanceof PhiInst) {
                phiCnt++;
                for (int i = 1; i < inst.getOperands().size(); i += 2) {
                    BasicBlock fromBlock = (BasicBlock) inst.getOperand(i);
                    if (predecessors.contains(fromBlock)) {
                        return false;
                    }
                }
            }
        }

        if (phiCnt > 0 && predecessors.size() > 1) return false;
        BasicBlock pred = predecessors.get(0);
        assert pred != null;

        for (Instruction inst : to.getInstructionList()) {
            if (inst instanceof PhiInst) {
                for (int i = 1; i < inst.getOperands().size(); i += 2) {
                    if (inst.getOperand(i) == from) {
                        inst.setOperand(i, pred);
                    }
                }
            }
        }

        from.replaceAllUsesWith(to);
        return true;
    }

    private void merge(BasicBlock block, BasicBlock rhs) {
        block.getInstructionList().remove(block.getLastInstruction());
        block.cancelTermination();
        for(Instruction inst : rhs.getInstructionList()) {
            if (inst instanceof PhiInst) {
                Value value = null;
                for (int i = 1; i < inst.getOperands().size(); i += 2) {
                    if (inst.getOperand(i) == block) {
                        value = inst.getOperand(i - 1);
                    }
                }
                assert value != null;
                inst.replaceAllUsesWith(value);
            } else {
                block.addInst(inst);
            }
        }
        rhs.getInstructionList().clear();
//        transfer(rhs, block);
        rhs.replaceAllUsesWith(block);
    }

    private void overwriteJumpOf(BasicBlock block) {
        BasicBlock to = (BasicBlock) block.getLastInstruction().getOperand(0);
        block.getInstructionList().remove(block.getLastInstruction());
        block.cancelTermination();
        block.addInst(to.getLastInstruction());
    }

    boolean onePass() {
        for (BasicBlock block : cfg.getRPO()) {
            Instruction last = block.getLastInstruction();

            if (last instanceof BranchInst) {
                BasicBlock br1 = (BasicBlock) last.getOperand(1);
                BasicBlock br2 = (BasicBlock) last.getOperand(2);
                if (br1 == br2) {
                    last.collapse();
                    block.getInstructionList().remove(last);
                    block.cancelTermination();
                    new JumpInst(br1, block);
//                    System.err.println("br Exterminate " + block.getIdentifier());
                    return true;
                }
            }

            //we should not delete entryBlock and exitBlock
            if (last instanceof JumpInst) {
                BasicBlock to = (BasicBlock) last.getOperand(0);
                if (to == block) continue;
                if (block.getInstructionList().size() == 1 && block != curFunction.entryBlock()) {
                    if (transfer(block, to)) {
//                        System.err.println("jump Exterminate " + block.getIdentifier());
                        return true;
                    }
                }
                if (cfg.getPredecessors(to).size() == 1
                        && to != curFunction.entryBlock() && to != curFunction.exitBlock()) {
                    merge(block, to);
//                    System.err.println("Merge " + block.getIdentifier() + " with " + to.getIdentifier());
                    return true;
                }
                if (to.getInstructionList().size() == 1 && to.getLastInstruction() instanceof BranchInst
                        && !to.getLastInstruction().getOperands().contains(to)) {
                    overwriteJumpOf(block);
//                    System.err.println("Overwrote " + block.getIdentifier());
                    return true;
                }
            }
        }
        return false;
    }

    void optimizeFunction(Function function) {
        curFunction = function;
        cfg = new CFG(function, function.entryBlock(), false);
        int cnt = 0;
        while(onePass()) {
//            print("code_" + cnt++ + ".ll", (new IRPrinter() {{visit(module);}}).getIR(false));
            cfg = new CFG(function, function.entryBlock(), false);
        }
    }
}
