package IR.Instruction;

import IR.BasicBlock;
import IR.User;
import Symbol.Type;

public abstract class Instruction extends User {
    Instruction(String name, Type type, BasicBlock curBlock) {
        super(name, type);
        curBlock.addInst(this);
    }
}
