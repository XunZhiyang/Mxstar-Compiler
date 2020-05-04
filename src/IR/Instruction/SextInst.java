package IR.Instruction;

import IR.BasicBlock;
import IR.Value;
import Symbol.Type;

public class SextInst extends Instruction {
    public SextInst(Value value, Type toType, BasicBlock curBlock) {
        super(".sext", toType, curBlock);
        addOperand(value);
    }
}
