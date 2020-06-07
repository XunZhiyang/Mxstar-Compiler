package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.User;
import IR.Value;
import Symbol.Type;

import java.util.ArrayList;

public abstract class Instruction extends User implements Cloneable {
    private BasicBlock fromBlock;

    Instruction(String name, Type type) {
        super(name, type);
    }

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

    public void collapse() {
        for (Value value : getOperands()) {
            if (value != null)
                value.deleteUse(this);
        }
    }

    public boolean isTerminator() { return false;}


    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public Instruction cloneInst() {
        Instruction inst = (Instruction) this.clone();
        inst.identifier = rename(this.identifier);
        inst.operands = new ArrayList<>();
        inst.uses = new ArrayList<>();
        this.operands.forEach(inst::addOperand);
        return inst;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
