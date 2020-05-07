package IR;

import IR.Constant.*;
import IR.Instruction.*;
import Symbol.ClassType;

public interface IRVisitor {
    void visit(Value value);

    void visit(Module node);

    void visit(Function node);

    void visit(ClassType node);

    void visit(GlobalVariable node);

    void visit(BasicBlock node);

    //Instruction
    void visit(AllocaInst node);

    void visit(BinaryOpInst node);

    void visit(BranchInst node);

    void visit(CallInst node);

    void visit(GEPInst node);

    void visit(JumpInst node);

    void visit(LoadInst node);

    void visit(ReturnInst node);

    void visit(SextInst node);

    void visit(StoreInst node);

    //Constant
    void visit(BoolConst node);

    void visit(IntConst node);

    void visit(NullConst node);

    void visit(StringConst node);

    //Normal value

//    void visit(Value node);
}
