package Optimizer;

import IR.Constant.Function;
import IR.Module;

public class Mem2Reg extends Pass {

    public Mem2Reg(Module module) {
        super(module);
    }

    @Override
    protected void optimizeFunction(Function function) {

    }
}
