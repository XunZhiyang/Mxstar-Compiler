package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

public class LuiInst extends InstRV {
    Register rd;
    Immediate immediate;
    public LuiInst(Register rd, Immediate immediate, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.immediate = immediate;
    }
}
