package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

public class RTypeInst extends InstRV{
    private String op;
    private Register rd, rs1, rs2;

    public RTypeInst(String op, Register rd, Register rs1, Register rs2, BlockRV curBlock) {
        super(curBlock);
        this.op = op;
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
    }

}
