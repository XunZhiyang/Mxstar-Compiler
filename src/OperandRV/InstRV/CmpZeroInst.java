package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

public class CmpZeroInst extends InstRV{
    private String op;             // seqz, snez
    private Register rd, rs;

    public CmpZeroInst(String op, Register rd, Register rs, BlockRV curBlock) {
        super(curBlock);
        this.op = op;
        this.rd = rd;
        this.rs = rs;
    }
}
