package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.List;

public abstract class InstRV {
    Register rd, rs1, rs2;
    Immediate immediate;

    public InstRV() {}

    public InstRV(BlockRV curBlock) {
        curBlock.add(this);
    }

    public List<Register> getUses() {
        return new ArrayList<>();
    }

    public List<Register> getDef() {
        return new ArrayList<>();
    }

    public boolean replaceUsesWith(Register old, Register replacement) {
        boolean flag = true;
        if (rs1 == old) {
            rs1 = replacement;
            flag = false;
        }
        if (rs2 == old) {
            rs2 = replacement;
            flag = false;
        }
        //            throw new RuntimeException();
        return !flag;
    }

    public void replaceDefWith(Register old, Register replacement) {
        if (rd == old) {
            rd = replacement;
        } else {
            throw new RuntimeException();
        }
    }

    public void adjustImmediate(int offset) {}

    public String getRdStr() {
        return rd.getIdentifier();
    }

    public String getRs1() {
        return rs1.getIdentifier();
    }

    public String getRs2() {
        return rs2.getIdentifier();
    }

    public String getImmediate() {
        return immediate.toString();
    }

    public int getImmediateNumber() {
        return immediate.getValue();
    }

    public void print(RVPrinter printer) {
        printer.print(this);
    }

    protected String convert(String op) {
        switch(op) {
            case "shl":
                return "sll";
            case "ashr":
                return "sra";
            case "srem":
                return "rem";
            case "sdiv":
                return "div";
            default:
                return op;
        }
    }
}
