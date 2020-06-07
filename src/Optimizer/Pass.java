package Optimizer;

import IR.Module;

public abstract class Pass {
    protected Module module;

    public Pass(Module module) {
        this.module = module;
    }

    abstract void optimize();

}
