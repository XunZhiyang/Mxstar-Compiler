package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.List;

public abstract class InstRV {
    public InstRV(BlockRV curBlock) {
        curBlock.add(this);
    }

    public List<Register> getUses() {
        return new ArrayList<>();
    }

    public Register getDef() {
        return null;
    }

    public void adjustImmediate(int offset) {}
}
