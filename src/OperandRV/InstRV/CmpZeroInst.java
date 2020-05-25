package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CmpZeroInst extends InstRV{
    private String op;             // seqz, snez
    private Register rd, rs;

    public CmpZeroInst(String op, Register rd, Register rs, BlockRV curBlock) {
        super(curBlock);
        this.op = op;
        this.rd = rd;
        this.rs = rs;
    }

    public List<Register> getUses() {
        return new ArrayList<>(Collections.singletonList(rs));
    }
    public Register getDef() {
        return rd;
    }
}
