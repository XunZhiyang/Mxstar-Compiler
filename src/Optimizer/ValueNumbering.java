package Optimizer;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Instruction.BinaryOpInst;
import IR.Instruction.CmpInst;
import IR.Instruction.Instruction;
import IR.Instruction.PhiInst;
import IR.Module;
import IR.Value;

import java.util.*;

public class ValueNumbering extends FunctionOptimizer{

    private static class Scope {

        private static class FuzzyInst {
            private Instruction instruction;

            FuzzyInst(Instruction instruction) {
                this.instruction = instruction;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof FuzzyInst)) return false;
                Instruction other = ((FuzzyInst) obj).instruction;
                if (other.getClass() != instruction.getClass()) return false;
                if (other.getOperands().size() != instruction.getOperands().size()) return false;
                for (int i = 0; i < other.getOperands().size(); ++i) {
                    if (other.getOperand(i) != instruction.getOperand(i)) return false;
                }
                if (instruction instanceof BinaryOpInst)
                    return ((BinaryOpInst) other).getOp().equals(((BinaryOpInst) instruction).getOp());
                if (instruction instanceof CmpInst)
                    return ((CmpInst) other).getOp().equals(((CmpInst) instruction).getOp());
                return true;
            }

            @Override
            public int hashCode() {
                int ret = 0;
                for (Value value : instruction.getOperands()) {
                    if (value != null)
                        ret ^= value.hashCode();
                }
                return ret;
            }
        }

        Scope fatherScope;
        Map<FuzzyInst, Value> instVN = new HashMap<>();

        Scope(Scope fatherScope) {
            this.fatherScope = fatherScope;
        }

        public Value getVN(Value value) {
            if (value instanceof Instruction) {
                FuzzyInst fuzzyInst = new FuzzyInst((Instruction) value);
                if (instVN.containsKey(fuzzyInst)) {
                    return instVN.get(fuzzyInst);
                } else {
                    if (fatherScope == null) return value;
                    return fatherScope.getVN(value);
                }
            }
            return value;
        }

        public boolean setVN(Instruction inst, Instruction twinInst) {
            FuzzyInst fuzzyInst = new FuzzyInst(twinInst);
            if (instVN.containsKey(fuzzyInst)) {
                Value value = instVN.get(fuzzyInst);
                inst.replaceAllUsesWith(value);
                return true;
            } else {
                instVN.put(new FuzzyInst(inst), inst);
                return false;
            }
        }
    }

    private DomTree domTree;

    public ValueNumbering(Module module) {
        super(module);
    }

    private void rewrite(BasicBlock block,  Scope fatherScope) {
        Scope scope = new Scope(fatherScope);
        var instIterator = block.getInstructionList().listIterator();
        while (instIterator.hasNext()) {
            Instruction instruction = instIterator.next();
            if (instruction instanceof PhiInst) {
                Set<Value> operands = new HashSet<>();
                for (int i = 0; i < instruction.getOperands().size(); i += 2) {
                    Value operand = instruction.getOperand(i);
                    if (operand != null) operands.add(scope.getVN(operand));
                }
                assert operands.size() > 0;
                if (operands.size() == 1) {
                    Value vn = operands.iterator().next();
                    instruction.replaceAllUsesWith(vn);
                    instIterator.remove();
                } else {
                     if (scope.setVN(instruction, instruction)) {
                         instIterator.remove();
                     }
                }

            } else {
                if (instruction.deterministic()) {
                    Instruction twinInst = instruction.cloneInst();
                    instruction.getOperands().forEach(op -> twinInst.replaceOperand(op, scope.getVN(op)));
                    if (scope.getVN(twinInst) == twinInst && twinInst.commutative()) {
                        twinInst.reverseOperands();
                    }
                    if (scope.setVN(instruction, twinInst)) {
                        instIterator.remove();
                    }
                }
            }
        }

        for (BasicBlock successor : block.getSuccessors()) {
            for (Instruction instruction : successor.getInstructionList()) {
                if (instruction instanceof PhiInst) {
                    for (int i = 0; i < instruction.getOperands().size(); i += 2) {
                        Value operand = instruction.getOperand(i);
                        if (operand == null) continue;
                        if (scope.getVN(operand) != operand) {
                            instruction.setOperand(i, scope.getVN(operand));
                        }
                    }
                }
            }
        }

        for (BasicBlock child : domTree.getChildren(block)) {
            rewrite(child, scope);
        }
    }

    @Override
    void optimizeFunction(Function function) {
        domTree = new DomTree(function, function.entryBlock(), false);
        rewrite(function.entryBlock(), null);
    }
}
