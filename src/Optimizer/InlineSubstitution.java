package Optimizer;

import IR.BasicBlock;
import IR.Constant.Constant;
import IR.Constant.Function;
import IR.Instruction.*;
import IR.Module;
import IR.User;
import IR.Value;
import Symbol.GlobalScope;

import java.util.*;

public class InlineSubstitution extends Pass {
    private final int FUNC_SIZE = 150;
    private final int MAX_TIMES = 7;

    private Function curFunction, inliningFunction;
    private BasicBlock curBlock;
    private Map<Function, Integer> instNum;
    private Map<Value, Value> copy;
    private Map<String, List<CallInst>> caller;

    class FunctionComparator implements Comparator<Function> {
        public int compare(Function u1, Function u2) {
            return Integer.compare(instNum.get(u1), instNum.get(u2));
        }
    }

    private void scanCalls() {
        caller = new HashMap<>();
        for (Function function : module.getFunctionList()) {
            for (BasicBlock block : function.getBasicBlockList()) {
                for (Instruction instruction : block.getInstructionList()) {
                    if (instruction instanceof CallInst) {
                        String identifier = ((CallInst) instruction).getFunctionIdentifier();
                        caller.putIfAbsent(identifier, new ArrayList<>());
                        caller.get(identifier).add((CallInst) instruction);
                    }
                }
            }
        }
    }

    public InlineSubstitution(Module module) {
        super(module);
    }

    private boolean inlinable(Function function) {
        int num = function.instNum();
        instNum.put(function, num);
        return num < FUNC_SIZE && !function.getIdentifier().equals("@_init");
    }

    private BasicBlock splitAt(CallInst splitInst) {
        BasicBlock nBlock = curFunction.add(curBlock.getIdentifier() + "_inline_"
                + inliningFunction.getIdentifier().substring(1));
        var iterator = curBlock.getInstructionList().listIterator();
        boolean start = false;
        while(iterator.hasNext()) {
            Instruction nowInst = iterator.next();
            if (!start) {
                if (nowInst == splitInst) {
                    nowInst.collapse();
                    iterator.remove();
                    start = true;
                }
            } else {
                iterator.remove();
                nBlock.addInst(nowInst);
            }
        }
        curBlock.cancelTermination();

        for (BasicBlock successor : nBlock.getSuccessors()) {
            for (Instruction inst : successor.getInstructionList()) {
                if (inst instanceof PhiInst) {
                    inst.replaceOperand(curBlock, nBlock);
                }
            }
        }
        return nBlock;
    }

    private void copyInst(Function function) {
        for (BasicBlock block : function.getBasicBlockList()) {
            BasicBlock toBlock = (BasicBlock) copy.get(block);
            for (Instruction instruction : block.getInstructionList()) {
                Instruction newInst = instruction.cloneInst();
                toBlock.addInst(newInst);
                copy.put(instruction, newInst);
            }
        }
        for (BasicBlock block : function.getBasicBlockList()) {
            BasicBlock toBlock = (BasicBlock) copy.get(block);
            for (Instruction newInst : toBlock.getInstructionList()) {
                var iterator = newInst.getOperands().listIterator();
                while (iterator.hasNext()) {
                    Value operand = iterator.next();
                    if (operand == null) continue;
                    if (operand instanceof Constant) continue;
                    Value copyValue = copy.get(operand);
                    if (copyValue == null) {
                        System.err.println("q");
                    }
                    assert copyValue != null;
                    operand.deleteUse(newInst);
                    iterator.set(copyValue);
                    copyValue.addUse(newInst);
                }
            }
        }
    }

    private Function grimPatron(Function function) {
        copy = new HashMap<>();
        Function newFunction = new Function(function.getIdentifier(), function.getType());
        for (int i = 0; i < function.getOperands().size(); ++i) {
            Value cloneValue = new Value(function.getOperand(i));
            copy.put(function.getOperand(i), cloneValue);
            newFunction.addOperand(cloneValue);
        }
        for (BasicBlock block : function.getBasicBlockList()) {
            copy.put(block, newFunction.add(block.getIdentifier()));
        }
        copyInst(function);
        return newFunction;
    }

    private void rewrite(CallInst instruction) {
        curBlock = instruction.getFromBlock();
        curFunction = curBlock.getFromFunction();
        copy = new HashMap<>();
        BasicBlock secondBlock = splitAt(instruction);

        for (int i = 0; i < instruction.getOperands().size(); ++i) {
            copy.put(inliningFunction.getOperand(i), instruction.getOperands().get(i));
        }
        for (BasicBlock block : inliningFunction.getBasicBlockList()) {
            copy.put(block, curFunction.add(block.getIdentifier()));
        }
        copyInst(inliningFunction);

        new JumpInst((BasicBlock) copy.get(inliningFunction.entryBlock()), curBlock);

        BasicBlock exitBlock = (BasicBlock) copy.get(inliningFunction.exitBlock());
        ReturnInst retInst = (ReturnInst) exitBlock.getLastInstruction();
        if (retInst.getOperands().size() > 0) {
            instruction.replaceAllUsesWith(retInst.getOperand(0));
        }
        retInst.collapse();
        exitBlock.getInstructionList().remove(retInst);
        exitBlock.cancelTermination();

        new JumpInst(secondBlock, exitBlock);
    }

    private void replace(Function function) {
        inliningFunction = function;
        List<CallInst> callList = caller.get(function.getIdentifier());
        if (callList == null) return;
        int times = Math.min(callList.size(), MAX_TIMES);
        inliningFunction = grimPatron(inliningFunction);
        for (int i = 0; i < times; ++i) {
            rewrite(callList.get(i));
        }
    }

    @Override
    public void optimize() {
        scanCalls();
        instNum = new HashMap<>();
        List<Function> inlinableFunctions = new ArrayList<>();
        for (Function function : module.getFunctionList()) {
            if (inlinable(function)) {
                inlinableFunctions.add(function);
            }
        }
        inlinableFunctions.sort(new FunctionComparator());
        inlinableFunctions.forEach(this::replace);
    }
}
