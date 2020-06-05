package Backend;

import Optimizer.Dead;
import Optimizer.Exterminator;
import Optimizer.Mem2Reg;

import IR.Module;

public class IROptimizer {
    private Mem2Reg mem2Reg;
    private Exterminator exterminator;
    private Dead dead;
    public IROptimizer(Module module) {
        mem2Reg = new Mem2Reg(module);
        exterminator = new Exterminator(module);
        dead = new Dead(module);
    }

    public void optimize() {
        mem2Reg.optimize();
//        exterminator.optimize();
//        dead.optimize();
//        exterminator.optimize();
    }
}
