package OperandRV.InstRV;

import OperandRV.BlockRV;

public class JumpInst extends InstRV {
    public BlockRV dest;
    public JumpInst(BlockRV dest, BlockRV curBlock) {
        super(curBlock);
        this.dest = dest;
    }
}
