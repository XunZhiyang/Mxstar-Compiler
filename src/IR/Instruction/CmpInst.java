package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.GlobalScope;

public class CmpInst extends Instruction {
    String op;

    public CmpInst(String op, Value src1, Value src2, BasicBlock curBlock) {
        super(op, GlobalScope.getBoolType(), curBlock);
        this.op = op;
        addOperand(src1, src2);
    }

    public String getOp() {
        return op;
    }

    @Override
    public boolean deterministic() { return true;}

    @Override
    public boolean commutative() {
        return op.equals("eq") || op.equals("ne");
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
