package Optimizer;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Instruction.*;
import IR.Module;
import IR.User;
import IR.Value;
import Symbol.PointerType;

import java.util.*;

public class Mem2Reg extends Pass {

    private Map<String, List<Instruction>> workList = new HashMap<>();
    private Map<String, Stack<Value>> curValue = new HashMap<>();

    public Mem2Reg(Module module) {
        super(module);
    }

    @Override
    protected void init() {
        workList.clear();
        curValue.clear();
    }

    private List<Instruction> getStoreList(Instruction instruction) {
        List<Instruction> storeList = new ArrayList<>();
        storeList.add(instruction);
        for (User useInst : instruction.getUses()) {
            if (useInst instanceof StoreInst && useInst.getOperand(1) == instruction &&
                    ((StoreInst) useInst).getFromBlock() != null) {
                storeList.add((StoreInst) useInst);
            }
        }
        return storeList;
    }

    private void rename(DomTree.Node now) {
        List<String> recover = new ArrayList<>();

        var iterator = now.block.getInstructionList().listIterator();
        while (iterator.hasNext()) {
            Instruction instruction = iterator.next();
            if (instruction instanceof AllocaInst) {
                iterator.remove();
            } else if (instruction instanceof LoadInst) {
                String identifier = instruction.getOperand(0).getIdentifier();
                if (workList.get(identifier) != null) {
                    iterator.remove();
                    Stack<Value> curStack = curValue.get(identifier);
                    if (curStack != null && !curStack.isEmpty()) {
                        instruction.replaceAllUsesWith(curStack.lastElement());
                    }
                }
            } else if (instruction instanceof StoreInst || instruction instanceof PhiInst) {
                Value newValue;
                String identifier;
                if (instruction instanceof StoreInst) {
                    newValue = instruction.getOperand(1);
                    identifier = newValue.getIdentifier();
                } else {
                    newValue = instruction;
                    identifier = ((PhiInst) newValue).getOrigName();
                }
                if (workList.get(identifier) != null) {
                    curValue.putIfAbsent(identifier, new Stack<>());
                    curValue.get(identifier).add(instruction instanceof StoreInst ? instruction.getOperand(0) :
                            instruction);
                    recover.add(identifier);
                    if (instruction instanceof StoreInst) {
                        iterator.remove();
                    }
                }
            }
        }

        for (BasicBlock block : now.block.getSuccessors()) {
            for (Instruction instruction : block.getInstructionList()) {
                if (instruction instanceof PhiInst) {
                    Stack<Value> stack = curValue.get(((PhiInst) instruction).getOrigName());
                    if (stack == null || stack.isEmpty()) {
                        instruction.addOperand(null, now.block);
                    } else {
                        instruction.addOperand(stack.lastElement(), now.block);
                    }
                }
            }
        }

        now.children.forEach(this::rename);

        recover.forEach(str -> curValue.get(str).pop());
    }

    @Override
    protected void optimizeFunction(Function function) {
        DomTree domTree = new DomTree(function, function.entryBlock(), false);

        for (BasicBlock block : function.getBasicBlockList()) {
            for (Instruction instruction : block.getInstructionList()) {
                if (instruction instanceof AllocaInst) {
                    workList.put(instruction.getIdentifier(), getStoreList(instruction));
                }
            }
        }

        for (List<Instruction> instructionList : workList.values()) {
            Value curValue = instructionList.get(0);
            Queue<BasicBlock> queue = new LinkedList<>();
            instructionList.forEach(inst -> queue.add(inst.getFromBlock()));
            Set<BasicBlock> visit = new HashSet<>();
            for (BasicBlock now = queue.poll(); now != null; now = queue.poll()) {
                for (BasicBlock frontier : domTree.getDF(now)) {
                    if (visit.contains(frontier)) continue;
                    queue.add(frontier);
                    new PhiInst(curValue.getIdentifier(), ((PointerType) curValue.getType()).getMember(), frontier);
                    visit.add(frontier);
                }
            }
        }

        rename(domTree.getRoot());
    }
}
