package OperandRV;

import IR.BasicBlock;
import IR.Instruction.Instruction;
import OperandRV.InstRV.InstRV;

import java.util.ArrayList;
import java.util.List;

public class BlockRV extends OperandRV {
    private List<InstRV> instructions = new ArrayList<>();

    public BlockRV(BasicBlock block) {
        this.identifier = block.getIdentifier();
        block.setCorRV(this);
    }

    public void add(InstRV instruction) {
        instructions.add(instruction);
    }


}
