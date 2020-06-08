package Backend;

import Optimizer.*;

import IR.Module;
import Utils.StringPrinter;

public class IROptimizer {
    private Module module;
    private Mem2Reg mem2Reg;
    private Exterminator exterminator;
    private Dead dead;
    private InlineSubstitution inlineSubstitution;
    private Global2Local global2Local;
    public IROptimizer(Module module) {
        this.module = module;
        mem2Reg = new Mem2Reg(module);
        exterminator = new Exterminator(module);
        dead = new Dead(module);
        inlineSubstitution = new InlineSubstitution(module);
        global2Local = new Global2Local(module);
    }

    private void brick() {
//        exterminator.optimize();
        dead.optimize();
//        exterminator.optimize();
    }

    public void optimize() {
        mem2Reg.optimize();
        brick();
//        StringPrinter.print("code.ll", (new IRPrinter() {{visit(module);}}).getIR(false));
        global2Local.optimize();
//        brick();
        mem2Reg.optimize();
//        StringPrinter.print("code_opt.ll", (new IRPrinter() {{visit(module);}}).getIR(false));
        brick();
        for (int i = 0; i < 2; ++i) {
            inlineSubstitution.optimize();
            brick();
        }
        exterminator.optimize();



//        for (int i = 0; i < 5; ++i) {
//        }
    }
}
