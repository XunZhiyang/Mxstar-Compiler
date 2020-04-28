package IR.Instruction;

import IR.BasicBlock;
import IR.Value;
import Symbol.GlobalScope;

public class BranchInst extends Instruction {
    public BranchInst(Value cond, BasicBlock taken, BasicBlock notTaken, BasicBlock curBlock) {
        super("", GlobalScope.voidType, curBlock);
        addOperand(cond, taken, notTaken);
    }
}
