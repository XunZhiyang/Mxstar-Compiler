package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.PointerType;

public class LoadInst extends Instruction {
    public LoadInst(Value value, BasicBlock curBlock) {
        super(".load", ((PointerType) value.getType()).getMember(), curBlock);
        addOperand(value);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
