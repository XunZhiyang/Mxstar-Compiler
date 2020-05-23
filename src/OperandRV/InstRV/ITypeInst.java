package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Immediate;
import OperandRV.Register;

public class ITypeInst extends InstRV {
    private String op;
    private Register rd, rs;
    private Immediate immediate;

    public ITypeInst(String op, Register rd, Register rs, Immediate immediate, BlockRV block) {
        super(block);
        this.op = op;
        this.rd = rd;
        this.rs = rs;
        this.immediate = immediate;
    }

}
