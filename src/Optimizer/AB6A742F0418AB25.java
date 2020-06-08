package Optimizer;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Constant.GlobalVariable;
import IR.Constant.StringConst;
import IR.Instruction.*;
import IR.Module;
import IR.Value;
import Symbol.PointerType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AB6A742F0418AB25 extends Pass {
    private Set<GlobalVariable> globals = new HashSet<>();
    private Map<String, Set<GlobalVariable>> mark = new HashMap<>();
    private Map<String, Set<GlobalVariable>> hasStore = new HashMap<>();
    private Map<String, Set<GlobalVariable>> noStore = new HashMap<>();
    private Map<Function, Set<GlobalVariable>> local = new HashMap<>();
    public AB6A742F0418AB25(Module module) {
        super(module);
    }

    private void init() {
        globals.clear();
        mark.clear();
        hasStore.clear();
        noStore.clear();
        local.clear();
    }

    private void putIfAbsent(String functionIdentifier) {
        mark.putIfAbsent(functionIdentifier, new HashSet<>());
        noStore.putIfAbsent(functionIdentifier, new HashSet<>(globals));
        hasStore.putIfAbsent(functionIdentifier, new HashSet<>());
    }

    private void gatherUses(Function function) {
        String identifier = function.getIdentifier();
        putIfAbsent(identifier);

        for (BasicBlock block : function.getBasicBlockList()) {
            for (Instruction instruction : block.getInstructionList()) {
                if (instruction instanceof LoadInst) {
                    Value v = instruction.getOperand(0);
                    if (v instanceof GlobalVariable) {
                        mark.get(identifier).add((GlobalVariable) v);
                    }
                }
                if (instruction instanceof StoreInst) {
                    Value v = instruction.getOperand(1);
                    if (v instanceof GlobalVariable) {
                        mark.get(identifier).add((GlobalVariable) v);
                        hasStore.get(identifier).add((GlobalVariable) v);
                        noStore.get(identifier).remove(v);
                    }
                }
            }
        }
    }

    private void passUses() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Function function : module.getFunctionList()) {
                String identifier = function.getIdentifier();
                mark.putIfAbsent(identifier, new HashSet<>());
                for (BasicBlock block : function.getBasicBlockList()) {
                    for (Instruction instruction : block.getInstructionList()) {
                        if (instruction instanceof CallInst) {
                            String callee = ((CallInst) instruction).getFunctionIdentifier();
                            if (callee.equals("@_init")) continue;
                            putIfAbsent(callee);

                            if (!mark.get(identifier).containsAll(mark.get(callee))) {
                                changed = true;
                                mark.get(identifier).addAll(mark.get(callee));
                            }
                            if (!noStore.get(callee).containsAll(noStore.get(identifier))) {
                                changed = true;
                                noStore.get(identifier).retainAll(noStore.get(callee));
                            }
                        }
                    }
                }
            }
        }
    }

    private void calcLocal(Function function) {
        local.put(function, noStore.get(function.getIdentifier()));
        Set<GlobalVariable> neverCalled = new HashSet<>(globals);
        for (BasicBlock block : function.getBasicBlockList()) {
            for (Instruction instruction : block.getInstructionList()) {
                if (instruction instanceof CallInst) {
                    String identifier = ((CallInst)instruction).getFunctionIdentifier();
                    neverCalled.removeAll(mark.get(identifier));
                }
            }
        }
        local.get(function).addAll(neverCalled);
    }

    void rewrite(Function function) {
        for (GlobalVariable variable : local.get(function)) {
            Instruction load = new LoadInst(variable);
            Instruction alloca = new AllocaInst(((PointerType) variable.getType()).getMember());
            Instruction store = new StoreInst(load, alloca);

            for (BasicBlock block : function.getBasicBlockList()) {
                for (Instruction instruction : block.getInstructionList()) {
                    instruction.replaceOperand(variable, alloca);
                }
            }
            int pos = function.getIdentifier().equals("@main") ? 1 : 0;
            function.entryBlock().posAdd(pos, store, alloca, load);
            if (hasStore.get(function.getIdentifier()).contains(variable)) {
                var value = new LoadInst(alloca);
                Instruction storeBack = new StoreInst(value, variable);
                function.exitBlock().backAdd(value, storeBack);
            }

        }
    }

    @Override
    public void optimize() {
        init();
        for (Value variable : module.getGlobalVariableList()) {
            if (!(variable instanceof StringConst)) {
                globals.add((GlobalVariable) variable);
            }
        }
        module.getFunctionList().forEach(this::gatherUses);
        passUses();
        module.getFunctionList().forEach(this::calcLocal);

        module.getFunctionList().forEach(this::rewrite);
    }
}
