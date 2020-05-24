package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.GlobalScope;

public class MoveInst extends Instruction {
    public MoveInst(Value from, Value to, BasicBlock curBlock) {
        super("", GlobalScope.getVoidType(), curBlock);
        addOperand(from, to);
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
