package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Register;

public class LaInst extends InstRV {
    Register rd, symbol;
    public LaInst(Register rd, Register symbol, BlockRV curBlock) {
        super(curBlock);
    }
}
