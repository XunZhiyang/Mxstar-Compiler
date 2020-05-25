package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ITypeInst extends InstRV {
    private String op;
    private Register rd, rs;
    private Immediate immediate;

    public ITypeInst(String op, Register rd, Register rs, Immediate immediate, BlockRV curBlock) {
        super(curBlock);
        this.op = op;
        this.rd = rd;
        this.rs = rs;
        this.immediate = immediate;
    }

    public List<Register> getUses() {
        return new ArrayList<>(Collections.singletonList(rs));
    }

    public Register getDef() {
        return rd;
    }

    @Override
    public void adjustImmediate(int offset) {
        immediate.adjust(offset);
    }


}
