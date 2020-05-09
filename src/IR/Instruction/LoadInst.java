package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.PointerType;
import Symbol.Type;

public class LoadInst extends Instruction {
    public static Type f(Value value) {
        System.out.println(value);
        return ((PointerType) value.getType()).getMember();
    }

    public LoadInst(Value value, BasicBlock curBlock) {
        super(".load", f(value), curBlock);
        addOperand(value);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
