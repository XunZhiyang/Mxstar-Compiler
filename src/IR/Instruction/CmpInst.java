package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.GlobalScope;

public class CmpInst extends Instruction {

    public CmpInst(String op, Value src1, Value src2, BasicBlock curBlock) {
        super(op, GlobalScope.getBoolType(), curBlock);
        addOperand(src1, src2);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
