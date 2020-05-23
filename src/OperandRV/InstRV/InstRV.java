package OperandRV.InstRV;

import OperandRV.BlockRV;

public class InstRV {

    public InstRV(BlockRV block) {
        block.add(this);
    }
}
