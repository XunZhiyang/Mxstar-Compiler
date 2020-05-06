package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.GlobalScope;

public class StoreInst extends Instruction {
    public StoreInst(Value value, Value dest, BasicBlock curBlock) {
        super("", GlobalScope.getVoidType(), curBlock);
        addOperand(value, dest);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
