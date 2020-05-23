package Backend;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInst;
import IR.Instruction.MoveInst;
import IR.Instruction.PhiInst;
import IR.Module;
import IR.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class SSADestructor {

    static private class ParallelCopy {
        Map<Value, ArrayList<Value>> map = new HashMap<>();
//        Map<Value, Integer> times = new HashMap<>();

        void add(Value from, Value to) {
            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(to);
        }
    }

    Map<BasicBlock, ParallelCopy> pcMap = new HashMap<>();

    private void revisePhi(BasicBlock block, Map<BasicBlock, BasicBlock> replace) {
        for (Instruction inst : block.getInstructionList()) {
            if (inst instanceof PhiInst) {
                List<Value> operands = inst.getOperands();
                for (int i = 1; i < operands.size(); i += 2) {
                    BasicBlock from = (BasicBlock) operands.get(i);
                    BasicBlock replacement = replace.get(from);
                    if (replacement != null) {
                        inst.setOperand(i, replacement);
                    }
                }
            }
        }
    }

    private void

    private void destructFunction(Function function) {
        for (BasicBlock block : function.getBasicBlockList()) {
            Map<BasicBlock, BasicBlock> replace = new HashMap<>();
            for (BasicBlock pred : function.predecessorsOf(block)) {
                if (pred.getSuccessors().size() > 1) {
                    BasicBlock imd = function.add(pred.getIdentifier() + "__" + block.getIdentifier());
                    new JumpInst(block, imd);
                    pred.redirect(block, imd);
                    replace.put(pred, imd);
                }
            }
            revisePhi(block, replace);
        }

        function.getBasicBlockList().forEach(block -> pcMap.put(block, new ParallelCopy()));

        for (BasicBlock block : function.getBasicBlockList()) {
            for (Instruction instruction : block.getInstructionList()){
                if (instruction instanceof PhiInst) {
                    List<Value> operands = instruction.getOperands();
                    for (int i = 0; i < operands.size(); i += 2) {
                        Value value = operands.get(i);
                        Value newValue = Value.genPrime(value);
                        BasicBlock fromBlock = (BasicBlock) operands.get(i + 1);
                        pcMap.get(fromBlock).add(value, newValue);
                        instruction.setOperand(i, newValue);
                    }
                }
            }
        }

        pcMap.forEach((basicBlock, parallelCopy) -> para2seq(basicBlock, parallelCopy))
    }

    public void destruct(Module module) {
        module.getFunctionList().forEach(this::destructFunction);
    }
}
