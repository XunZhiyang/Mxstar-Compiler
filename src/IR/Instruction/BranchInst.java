package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.GlobalScope;

public class BranchInst extends Instruction {
    public BranchInst(Value cond, BasicBlock taken, BasicBlock notTaken, BasicBlock curBlock) {
        super("", GlobalScope.getVoidType(), curBlock);
        addOperand(cond, taken, notTaken);
    }

    @Override
    public boolean isTerminator() { return true;}

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
