package Backend;

import IR.Constant.*;
import IR.IRVisitor;
import IR.Instruction.*;
import IR.Module;
import IR.Value;
import Symbol.ClassType;

public class IRPrinter implements IRVisitor {
    private String res;

    public String getRes() {
        return res;
    }

    @Override
    public void visit(Value node) {

    }

    @Override
    public void visit(Module node) {
        for (Value i : node.getGlobalVariableList()) {
            res += i.getIdentifier() + " = global " + i.getType().getMember().IR;


        }
    }

    @Override
    public void visit(Function node) {

    }

    @Override
    public void visit(ClassType node) {

    }

    @Override
    public void visit(GlobalVariable node) {
        res += node.getIdentifier();
    }

    //Instruction
    @Override
    public void visit(AllocaInst node) {

    }

    @Override
    public void visit(BinaryOpInst node) {

    }

    @Override
    public void visit(BranchInst node) {

    }

    @Override
    public void visit(CallInst node) {

    }

    @Override
    public void visit(GEPInst node) {

    }

    @Override
    public void visit(JumpInst node) {

    }

    @Override
    public void visit(LoadInst node) {

    }

    @Override
    public void visit(ReturnInst node) {

    }

    @Override
    public void visit(SextInst node) {

    }

    @Override
    public void visit(StoreInst node) {

    }

    //Constant
    @Override
    public void visit(BoolConst node) {

    }

    @Override
    public void visit(IntConst node) {

    }

    @Override
    public void visit(NullConst node) {

    }

    @Override
    public void visit(StringConst node) {

    }

}
