package Optimizer;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Instruction.*;
import IR.Module;
import IR.Value;

import java.util.HashSet;
import java.util.Set;

public class Dead extends FunctionOptimizer {
    private DomTree reverseDomTree;
    private Set<Instruction> mark;
    private Set<BasicBlock> blockMark;
    private Set<Instruction> workList;

    @Override
    void init() {
        mark = new HashSet<>();
        blockMark = new HashSet<>();
        workList = new HashSet<>();
    }

    public Dead(Module module) {
        super(module);
    }

    void mark(Function function) {
        reverseDomTree = new DomTree(function, function.exitBlock(), true);

        for (BasicBlock block : function.getBasicBlockList()) {
            for (Instruction inst : block.getInstructionList()) {
                if (inst instanceof ReturnInst || inst instanceof CallInst || inst instanceof StoreInst) {
                    mark.add(inst);
                    workList.add(inst);
                }
            }
        }

        while (!workList.isEmpty()) {
            var iterator = workList.iterator();
            Instruction instruction = iterator.next();
            iterator.remove();
            blockMark.add(instruction.getFromBlock());

            for (Value value : instruction.getOperands()) {
                Instruction def = null;
                if (value instanceof Instruction) {
                    def = (Instruction) value;
                }
                if (value instanceof BasicBlock) {
                    def = ((BasicBlock) value).getLastInstruction();
                }
                if (def != null && !mark.contains(def)) {
                    mark.add(def);
                    workList.add(def);
                }
            }

            for (BasicBlock block : reverseDomTree.getDF(instruction.getFromBlock())) {
                if (!mark.contains(block.getLastInstruction())) {
                    mark.add(block.getLastInstruction());
                    workList.add(block.getLastInstruction());
                }
            }
        }
    }

    void sweep(Function function) {
        for (BasicBlock block : function.getBasicBlockList()) {
            var iterator = block.getInstructionList().listIterator();
            while (iterator.hasNext()) {
                Instruction instruction = iterator.next();
                if (!mark.contains(instruction)) {
                    if (instruction instanceof BranchInst) {
                        instruction.collapse();
                        BasicBlock nBlock = reverseDomTree.getIDom(block);
                        for (; !blockMark.contains(nBlock); nBlock = reverseDomTree.getIDom(nBlock));
                        iterator.set(new JumpInst(nBlock) {{setFromBlock(block);}});
                    } else if (!(instruction instanceof JumpInst)) {
                        instruction.collapse();
                        iterator.remove();
                    }
                }
            }
        }
    }

    @Override
    void optimizeFunction(Function function) {
        mark(function);
        sweep(function);
    }
}
