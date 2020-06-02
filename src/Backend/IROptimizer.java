package Backend;

import Optimizer.Exterminator;
import Optimizer.Mem2Reg;

import IR.Module;

public class IROptimizer {
    private Mem2Reg mem2Reg;
    private Exterminator exterminator;
    public IROptimizer(Module module) {
        mem2Reg = new Mem2Reg(module);
        exterminator = new Exterminator(module);
    }

    public void optimize() {
        mem2Reg.optimize();
        exterminator.optimize();
    }
}
