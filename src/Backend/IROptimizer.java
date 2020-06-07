package Backend;

import Optimizer.Dead;
import Optimizer.Exterminator;
import Optimizer.InlineSubstitution;
import Optimizer.Mem2Reg;

import IR.Module;
import Utils.StringPrinter;

public class IROptimizer {
    private Module module;
    private Mem2Reg mem2Reg;
    private Exterminator exterminator;
    private Dead dead;
    private InlineSubstitution inlineSubstitution;
    public IROptimizer(Module module) {
        this.module = module;
        mem2Reg = new Mem2Reg(module);
        exterminator = new Exterminator(module);
        dead = new Dead(module);
        inlineSubstitution = new InlineSubstitution(module);
    }

    public void optimize() {
        mem2Reg.optimize();
//        exterminator.optimize();
//        dead.optimize();
//        exterminator.optimize();
//        StringPrinter.print("code.ll", (new IRPrinter() {{visit(module);}}).getIR(false));
//        inlineSubstitution.optimize();
//        StringPrinter.print("code_opt.ll", (new IRPrinter() {{visit(module);}}).getIR(false));

//        for (int i = 0; i < 5; ++i) {
//        }
    }
}
