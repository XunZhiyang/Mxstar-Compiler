package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;

public class ReturnInst extends InstRV{
    public ReturnInst(BlockRV curBlock) {
        super(curBlock);
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }

}
