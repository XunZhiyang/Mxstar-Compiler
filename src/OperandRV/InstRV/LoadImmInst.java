package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Register;

public class LoadImmInst extends InstRV {
    Register rd;
    int value;
    public LoadImmInst(Register rd, int value, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.value = value;
    }
}
