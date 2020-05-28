package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Register;

import java.util.Collections;
import java.util.List;

public class LaInst extends InstRV {
    Register symbol;
    public LaInst(Register rd, Register symbol, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.symbol = symbol;
    }

    public List<Register> getDef() {
        return Collections.singletonList(rd);
    }

    public String getSymbol() {
        return symbol.getIdentifier();
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }
}
