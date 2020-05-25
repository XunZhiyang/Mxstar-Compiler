package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranchInst extends InstRV {
    private Register rs1, rs2;
    private BlockRV dest;
    private String op;

    public BranchInst(Register rs1, Register rs2, String op, BlockRV dest, BlockRV curBlock) {
        super(curBlock);
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.op = op;
        this.dest = dest;
    }

    @Override
    public List<Register> getUses() {
        return new ArrayList<>(Arrays.asList(rs1, rs2));
    }
}
