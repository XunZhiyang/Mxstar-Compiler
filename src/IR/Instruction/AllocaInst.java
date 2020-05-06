package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import Symbol.Type;

public class AllocaInst extends Instruction {
    public AllocaInst(Type type, BasicBlock curBlock) {
        super(".alloca", type.getPointer(), curBlock);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
