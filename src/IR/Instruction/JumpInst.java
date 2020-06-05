package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.GlobalScope;

public class JumpInst extends Instruction {
    public JumpInst(BasicBlock jumpTo, BasicBlock curBlock) {
        super("", GlobalScope.getVoidType(), curBlock);
        addOperand(jumpTo);
    }

    public JumpInst(BasicBlock jumpTo) {
        super("", GlobalScope.getVoidType());
        addOperand(jumpTo);
    }

    @Override
    public boolean isTerminator() {return true;}

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
