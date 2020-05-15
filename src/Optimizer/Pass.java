package Optimizer;

import IR.Constant.Function;
import IR.Module;

public abstract class Pass {
    private Module module;

    public Pass(Module module) {
        this.module = module;
    }

    abstract void optimizeFunction(Function function);

    public void optimize() {
        module.getFunctionList().forEach(function -> {
            optimizeFunction(function);
        });
    }
}
