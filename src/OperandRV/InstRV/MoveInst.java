package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveInst extends InstRV {
//    private Register rd, rs1;

    public MoveInst(Register rd, Register rs1, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.rs1 = rs1;
    }

    public List<Register> getUses() {
        return new ArrayList<>(Collections.singletonList(rs1));
    }

    public List<Register> getDef() {
        return Collections.singletonList(rd);
    }

    public Register getRd() {
        return rd;
    }

    public Register getRs() {
        return rs1;
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }
}
