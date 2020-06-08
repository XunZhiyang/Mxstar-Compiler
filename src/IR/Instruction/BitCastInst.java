package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.Type;

public class BitCastInst extends Instruction{
    public BitCastInst(Value value, Type toType, BasicBlock curBlock) {
        super("bitCast", toType, curBlock);
        addOperand(value);
    }

    @Override
    public boolean deterministic() { return true;}

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
