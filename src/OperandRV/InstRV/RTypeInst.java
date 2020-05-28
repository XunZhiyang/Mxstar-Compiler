package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RTypeInst extends InstRV{
    private String op;
//    private Register rd, rs1, rs2;

    public RTypeInst(String op, Register rd, Register rs1, Register rs2, BlockRV curBlock) {
        super(curBlock);
        this.op = convert(op);
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
    }

    public List<Register> getUses() {
        return new ArrayList<>(Arrays.asList(rs1, rs2));
    }

    public List<Register> getDef() {
        return Collections.singletonList(rd);
    }

    public String getOp() {
        return op;
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }

}
