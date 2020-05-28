package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StoreInst extends InstRV {
//    Register rd, rs1;
//    Immediate immediate;
    int byteNum;

    public StoreInst(Register rs1, Register rs2, Immediate immediate, int byteNum, BlockRV curBlock) {
        super(curBlock);
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.immediate = immediate;
        this.byteNum = byteNum;
    }

    public StoreInst(Register rs1, Register rs2, Immediate immediate, int byteNum) {
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.immediate = immediate;
        this.byteNum = byteNum;
    }


    public List<Register> getUses() {
        return new ArrayList<>(Arrays.asList(rs1, rs2));
    }

    @Override
    public void adjustImmediate(int offset) {
        immediate.adjust(offset);
    }

    public String getOp() {
        return byteNum == 1 ? "sb" : "sw";
    }

    public String getSrc() {
        return rs1.isGlobal() ? getRs1() : (getImmediate() + "(" + getRs1() + ")");
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }
}
