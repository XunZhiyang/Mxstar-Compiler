package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ITypeInst extends InstRV {
    private String op;
//    private Register rd, rs1;
//    private Immediate immediate;

    public ITypeInst(String op, Register rd, Register rs1, Immediate immediate, BlockRV curBlock) {
        super(curBlock);
        this.op = convert(op);
        this.rd = rd;
        this.rs1 = rs1;
        this.immediate = immediate;
    }

    public List<Register> getUses() {
        return new ArrayList<>(Collections.singletonList(rs1));
    }

    public List<Register> getDef() {
        return Collections.singletonList(rd);
    }

    @Override
    public void adjustImmediate(int offset) {
        immediate.adjust(offset);
    }

    public String getOp() {
        return op + "i";
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }
}
