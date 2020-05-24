package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

public class LoadInst extends InstRV{
    private Register rd, rs;
    private Immediate offset;
    private int byteNum;

    public LoadInst(Register rd, Register rs, Immediate offset, int byteNum, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.rs = rs;
        this.offset = offset;
        this.byteNum = byteNum;
    }
}
