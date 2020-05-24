package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Register;

public class MoveInst extends InstRV {
    Register rd, rs;

    public MoveInst(Register rd, Register rs, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.rs = rs;
    }
}
