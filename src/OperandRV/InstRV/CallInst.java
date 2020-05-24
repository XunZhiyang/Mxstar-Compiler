package OperandRV.InstRV;

import OperandRV.BlockRV;
import OperandRV.FunctionRV;

public class CallInst extends InstRV {
    private FunctionRV function;

    public CallInst(FunctionRV function, BlockRV curBlock) {
        super(curBlock);
        this.function = function;
    }
}
