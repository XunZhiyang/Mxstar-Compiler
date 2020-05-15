package Backend;

import Optimizer.Mem2Reg;

public class IROptimizer {
    private Mem2Reg mem2Reg;
    public IROptimizer(Module module) {
        mem2Reg = new Mem2Reg(module);
    }

    public void optimize() {
        mem2Reg.optimize();
    }
}
