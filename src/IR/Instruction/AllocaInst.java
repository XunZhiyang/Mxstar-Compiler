package IR.Instruction;

import IR.BasicBlock;
import Symbol.Type;

public class AllocaInst extends Instruction {
    public AllocaInst(Type type, BasicBlock curBlock) {
        super("alloca", type.getPointer(), curBlock);
    }
}
