package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CmpZeroInst extends InstRV{
    private String op;             // seqz, snez
//    private Register rd, rs;

    public CmpZeroInst(String op, Register rd, Register rs1, BlockRV curBlock) {
        super(curBlock);
        this.op = op;
        this.rd = rd;
        this.rs1 = rs1;
    }

    public List<Register> getUses() {
        return new ArrayList<>(Collections.singletonList(rs1));
    }
    public List<Register> getDef() {
        return new ArrayList<>(Collections.singletonList(rd));
    }

    public String getOp() {
        return op;
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }
}
