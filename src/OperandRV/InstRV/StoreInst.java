package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

public class StoreInst extends InstRV {
    Register rd, rs;
    Immediate offset;
    int byteNum;

    public StoreInst(Register rd, Register rs, Immediate offset, int byteNum, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.rs = rs;
        this.offset = offset;
        this.byteNum = byteNum;
    }
}
