package IR.Instruction;

import IR.BasicBlock;
import IR.Value;
import Symbol.GlobalScope;

public class BranchInst extends Instruction {
    private Value cond;
    private BasicBlock taken, notTaken;

    public BranchInst(BasicBlock jumpTo, BasicBlock curBlock) {
        super("", GlobalScope.voidType, curBlock);
        this.taken = jumpTo;
    }

    public BranchInst(Value cond, BasicBlock taken, BasicBlock notTaken, BasicBlock curBlock) {
        super("", GlobalScope.voidType, curBlock);
        this.cond = cond;
        this.taken = taken;
        this.notTaken = notTaken;
    }
}
