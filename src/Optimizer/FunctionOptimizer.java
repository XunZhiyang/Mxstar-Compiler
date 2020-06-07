package Optimizer;

import IR.Constant.Function;
import IR.Module;

public abstract class FunctionOptimizer extends Pass {
    public FunctionOptimizer(Module module) {
        super(module);
    }

    abstract void optimizeFunction(Function function);

    void init(){}

    @Override
    public void optimize() {
        for (Function function : module.getFunctionList()) {
            init();
            optimizeFunction(function);
        }
    }
}
