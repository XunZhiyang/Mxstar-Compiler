package Backend;

import Optimizer.*;

import IR.Module;

public class IROptimizer {
    private Mem2Reg mem2Reg;
    private Exterminator exterminator;
    private Dead dead;
    private InlineSubstitution inlineSubstitution;
    private AB6A742F0418AB25 AB6A742F0418AB25;
    private ValueNumbering valueNumbering;
    public IROptimizer(Module module) {
        mem2Reg = new Mem2Reg(module);
        exterminator = new Exterminator(module);
        dead = new Dead(module);
        inlineSubstitution = new InlineSubstitution(module);
        AB6A742F0418AB25 = new AB6A742F0418AB25(module);
        valueNumbering = new ValueNumbering(module);
    }

    public void optimize() {
        mem2Reg.optimize();
        valueNumbering.optimize();
        dead.optimize();
        AB6A742F0418AB25.optimize();
        valueNumbering.optimize();
        mem2Reg.optimize();
        valueNumbering.optimize();
        dead.optimize();
        inlineSubstitution.optimize(5);
        inlineSubstitution.optimize(20);
        valueNumbering.optimize();
        dead.optimize();
        exterminator.optimize();
    }
}
