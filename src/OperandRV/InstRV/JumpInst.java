package OperandRV.InstRV;

import OperandRV.BlockRV;

public class JumpInst extends InstRV {
    private BlockRV dest;
    public JumpInst(BlockRV dest, BlockRV curBlock) {
        super(curBlock);
        this.dest = dest;
    }

    public BlockRV getDest() {
        return dest;
    }
}
