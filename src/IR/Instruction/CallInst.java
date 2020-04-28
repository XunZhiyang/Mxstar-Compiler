package IR.Instruction;

import IR.BasicBlock;
import IR.Constant.Function;
import Symbol.Type;

public class CallInst extends Instruction {
    public CallInst(Function function, BasicBlock basicBlock) {
        super(function.getIdentifier() + "_call", function.getType(), basicBlock);
    }
}
