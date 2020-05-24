package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Register;

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
}
