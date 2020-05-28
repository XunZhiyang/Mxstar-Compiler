package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadInst extends InstRV{
//    private Register rd, rs1;
//    private Immediate immediate;
    private int byteNum;

    public LoadInst(Register rd, Register rs1, Immediate immediate, int byteNum, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.rs1 = rs1;
        this.immediate = immediate;
        this.byteNum = byteNum;
    }

    public LoadInst(Register rd, Register rs1, Immediate immediate, int byteNum) {
        super();
        this.rd = rd;
        this.rs1 = rs1;
        this.immediate = immediate;
        this.byteNum = byteNum;
    }

    public List<Register> getUses() {
        if (rs1.isGlobal()) {
            return new ArrayList<>();
        }
        else {
            return new ArrayList<>(Collections.singletonList(rs1));
        }
    }

    public List<Register> getDef() {
        return Collections.singletonList(rd);
    }

    @Override
    public void adjustImmediate(int offset) {
        immediate.adjust(offset);
    }

    public String getOp() {
        return byteNum == 1 ? "lb" : "lw";
    }

    public String getSrc() {
        return rs1.isGlobal() ? getRs1() : (getImmediate() + "(" + getRs1() + ")");
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }
}
