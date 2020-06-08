package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.Type;

public class SextInst extends Instruction {
    public SextInst(Value value, Type toType, BasicBlock curBlock) {
        super(".sext", toType, curBlock);
        addOperand(value);
    }

    @Override
    public boolean deterministic() { return true;}

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
