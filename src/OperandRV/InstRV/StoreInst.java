package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreInst extends InstRV {
    Register rd, rs;
    Immediate immediate;
    int byteNum;

    public StoreInst(Register rd, Register rs, Immediate immediate, int byteNum, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.rs = rs;
        this.immediate = immediate;
        this.byteNum = byteNum;
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
