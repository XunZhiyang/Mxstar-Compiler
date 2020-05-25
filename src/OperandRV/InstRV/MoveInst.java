package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveInst extends InstRV {
    private Register rd, rs;

    public MoveInst(Register rd, Register rs, BlockRV curBlock) {
        super(curBlock);
        this.rd = rd;
        this.rs = rs;
    }

    public List<Register> getUses() {
        return new ArrayList<>(Collections.singletonList(rs));
    }

    public Register getDef() {
        return rd;
    }

    public Register getRd() {
        return rd;
    }

    public Register getRs() {
        return rs;
    }
}
