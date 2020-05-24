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

public class SSADestructor {

    static private class ParallelCopy {
        Map<Value, ArrayList<Value>> map = new HashMap<>();

        void add(Value from, Value to) {
            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(to);
        }

        boolean done() {
            boolean flag = true;
            var mapIterator = map.entrySet().iterator();
            while (mapIterator.hasNext()) {
                var entry = mapIterator.next();
                var iterator = entry.getValue().listIterator();
                while (iterator.hasNext()) {
                    Value v = iterator.next();
                    if (entry.getKey() != v) {
                        flag = false;
                    } else {
                        iterator.remove();
                    }
                }
                if (entry.getValue().isEmpty()) {
                    mapIterator.remove();
                }
            }
            return flag;
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

    private void para2seq(BasicBlock curBlock, ParallelCopy pc) {
        List<Instruction> instructions = curBlock.getInstructionList();
        while(!pc.done()) {
            boolean flag = false;
            for (var entry : pc.map.entrySet()) {
                var iterator = entry.getValue().listIterator();
                while (iterator.hasNext()) {
                    Value v = iterator.next();
                    if (pc.map.get(v) == null || pc.map.get(v).isEmpty()) {
                        flag = true;
                        MoveInst inst = new MoveInst(entry.getKey(), v, curBlock);
                        instructions.add(instructions.size() - 1, inst);
                        iterator.remove();
                    }
                }
            }

            if (!flag) {
                for (var entry : pc.map.entrySet()) {
                    Value from = entry.getKey();
                    Value to = entry.getValue().get(0);
                    Value newValue = Value.genPrime(from);
                    new MoveInst(from, newValue, curBlock);
                    entry.getValue().remove(0);
                    pc.add(newValue, to);
                    break;
                }
            }
        }
    }

    private BasicBlock redirect(BasicBlock curBlock) {
        List<Instruction> instructions = curBlock.getInstructionList();
        if (instructions.size() == 1 && instructions.get(0) instanceof JumpInst) {
            BasicBlock dest = (BasicBlock) instructions.get(0).getOperand(0);
            BasicBlock newDest = redirect(dest);
            curBlock.redirect(dest, newDest);
            return newDest;
        }
        return curBlock;
    }

    private void destructFunction(Function function) {
        List<BasicBlock> toBeAdded = new ArrayList<>();
        for (BasicBlock block : function.getBasicBlockList()) {
            Map<BasicBlock, BasicBlock> replace = new HashMap<>();
            List<BasicBlock> predecessors = function.predecessorsOf(block);
            if (predecessors.size() > 1) {
                for (BasicBlock pred : predecessors) {
                    if (pred.getSuccessors().size() > 1) {
                        BasicBlock imd = new BasicBlock(pred.getIdentifier() + "__" + block.getIdentifier());
                        toBeAdded.add(imd);
                        new JumpInst(block, imd);
                        pred.redirect(block, imd);
                        replace.put(pred, imd);
                    }
                }
                revisePhi(block, replace);
            }
        }

        toBeAdded.forEach(block -> function.getBasicBlockList().add(block));
        function.getBasicBlockList().forEach(block -> pcMap.put(block, new ParallelCopy()));

        for (BasicBlock block : function.getBasicBlockList()) {
            var iterator = block.getInstructionList().listIterator();
            while (iterator.hasNext()) {
                Instruction instruction = iterator.next();
                if (instruction instanceof PhiInst) {
                    List<Value> operands = instruction.getOperands();
                    for (int i = 0; i < operands.size(); i += 2) {
                        Value value = operands.get(i);
                        if (value == null) continue;
                        BasicBlock fromBlock = (BasicBlock) operands.get(i + 1);
                        pcMap.get(fromBlock).add(value, instruction);
                    }
                    iterator.remove();
                }
            }
        }
        pcMap.forEach(this::para2seq);

        function.getBasicBlockList().forEach(this::redirect);
        function.getBasicBlockList().removeIf(block ->
                function.predecessorsOf(block).isEmpty() && block != function.entryBlock());
    }





    public void destruct(Module module) {
        module.getFunctionList().forEach(this::destructFunction);
    }
}
