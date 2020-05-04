package IR.Instruction;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Value;
import Symbol.Type;

import java.util.List;

public class CallInst extends Instruction {
    public CallInst(Function function, List<Value> arguments, BasicBlock basicBlock) {
        super(function.getIdentifier() + "_call", function.getType(), basicBlock);
        addOperand(function);
        for (Value v : arguments) {
            addOperand(v);
        }
    }
}
