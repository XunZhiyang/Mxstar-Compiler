package IR.Instruction;

import IR.BasicBlock;
import IR.Value;
import Symbol.GlobalScope;

public class JumpInst extends Instruction {
    public JumpInst(BasicBlock jumpTo, BasicBlock curBlock) {
        super("", GlobalScope.getVoidType(), curBlock);
        addOperand(jumpTo);
    }

    @Override
    public boolean isTerminator() {return true;}
}
