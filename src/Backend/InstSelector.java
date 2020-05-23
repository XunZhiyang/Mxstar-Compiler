package Backend;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Module;
import IR.Constant.Function;
import OperandRV.*;
import OperandRV.InstRV.ITypeInst;
import OperandRV.InstRV.MoveInst;

import java.util.ArrayList;
import java.util.List;

public class InstSelector extends IRVisitor {
    private ModuleRV moduleRV = new ModuleRV();
    private FunctionRV curFunction;
    private int regCnt;

    public void visit(Module module) {
        for (Function function : module.getFunctionList()) {
            FunctionRV functionRV = new FunctionRV(function);
            moduleRV.getFunctions().add(functionRV);

            for (BasicBlock block : function.getBasicBlockList()) {
                functionRV.addBlock(new BlockRV(block));
            }
            function.getOperands().forEach(v -> functionRV.getParams().add(new VRegister(v)));
            function.accept(this);
        }
    }

    public void visit(Function function) {
        regCnt = 0;
        FunctionRV functionRV = (FunctionRV) function.getCorRV();
        curFunction = functionRV;
        Immediate immediate = new Immediate(0, true);
        new ITypeInst("addi", RV32.get("sp"), RV32.get("sp"), immediate, functionRV.entryBlock());
        List<Register> calleeSave = new ArrayList<>();
        for (String str : RV32.calleeSave) {
            VRegister reg = new VRegister(regCnt++, 4);
            new MoveInst(reg, RV32.get(str), functionRV.entryBlock());
        }
    }

}
