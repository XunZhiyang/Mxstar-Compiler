package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.GlobalScope;

public class ReturnInst extends Instruction {

    public ReturnInst(BasicBlock curBlock) {
        super("", GlobalScope.getVoidType(), curBlock);
    }

    public ReturnInst(Value value, BasicBlock curBlock) {
        super("", GlobalScope.getVoidType(), curBlock);
        addOperand(value);
    }

    @Override
    public boolean isTerminator() { return true;}

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
