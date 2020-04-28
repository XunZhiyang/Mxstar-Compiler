package IR.Instruction;

import IR.BasicBlock;
import IR.Value;
import Symbol.GlobalScope;

public class JumpInst extends Instruction {
    public JumpInst(BasicBlock jumpTo, BasicBlock curBlock) {
        super("", GlobalScope.voidType, curBlock);
        addOperand(jumpTo);
    }
}
