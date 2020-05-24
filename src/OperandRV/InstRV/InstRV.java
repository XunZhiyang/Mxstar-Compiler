package OperandRV.InstRV;

import OperandRV.BlockRV;

public class InstRV {

    public InstRV(BlockRV curBlock) {
        curBlock.add(this);
    }
}
