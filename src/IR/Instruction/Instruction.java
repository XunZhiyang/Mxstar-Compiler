package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.User;
import Symbol.Type;

public abstract class Instruction extends User {
    private BasicBlock fromBlock;

    Instruction(String name, Type type, BasicBlock curBlock) {
        super(name, type);
        curBlock.addInst(this);
    }

    Instruction(String name, Type type, BasicBlock curBlock, boolean front) {
        super(name, type);
        curBlock.addFront(this);
    }

    public void setFromBlock(BasicBlock fromBlock) {
        this.fromBlock = fromBlock;
    }

    public BasicBlock getFromBlock() {
        return fromBlock;
    }

    public boolean isTerminator() { return false;}

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
