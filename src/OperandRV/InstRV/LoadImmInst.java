package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Register;

import java.util.Collections;
import java.util.List;

public class LoadImmInst extends InstRV {
//    Register rd;
    int value;
    public LoadImmInst(Register rd, int value, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.value = value;
    }

    public List<Register> getDef() {
        return Collections.singletonList(rd);
    }

    public int getValue() {
        return value;
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }
}
