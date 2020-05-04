package IR.Instruction;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Value;
import Symbol.FunctionSymbol;
import Symbol.Type;

import java.util.List;

public class CallInst extends Instruction {
    String functionIdentifier;

    public CallInst(FunctionSymbol function, List<Value> arguments, BasicBlock basicBlock) {
        super(function.getIdentifier() + "_call", function.getType(), basicBlock);
        functionIdentifier = function.IRName();
        for (Value v : arguments) {
            addOperand(v);
        }
    }
}
