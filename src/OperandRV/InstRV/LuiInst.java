package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LuiInst extends InstRV {
//    Register rd;
//    Immediate immediate;
    public LuiInst(Register rd, Immediate immediate, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.immediate = immediate;
    }

    public List<Register> getDef() {
        return Collections.singletonList(rd);
    }

    @Override
    public void adjustImmediate(int offset) {
        immediate.adjust(offset);
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }
}
