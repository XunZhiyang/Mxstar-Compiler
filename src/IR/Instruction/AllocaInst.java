package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import Symbol.PointerType;
import Symbol.Type;

public class AllocaInst extends Instruction {
    public AllocaInst(Type type, BasicBlock curBlock) {
        super("alloca", type.derivesFromClass() ? type.getPointer().getPointer() : type.getPointer(), curBlock);
    }

    public AllocaInst(Type type, BasicBlock curBlock, boolean convert) {
        super("alloca", type.getPointer(), curBlock);
    }

    public AllocaInst(Type type) {
        super("alloca", type.getPointer());
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
