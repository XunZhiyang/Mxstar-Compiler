package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import Symbol.Type;

public class PhiInst extends Instruction {
    String origName;

    public PhiInst(String name, Type type, BasicBlock curBlock) {
        super("phi_" + name.substring(1) + ".", type, curBlock, true);
        origName = name;
        System.err.println("new Phi node for " + name + " at " + curBlock.getIdentifier());
    }

    public String getOrigName() {
        return origName;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
