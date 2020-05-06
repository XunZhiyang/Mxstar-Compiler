package IR.Instruction;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Value;
import Symbol.FunctionSymbol;
import Symbol.Type;

import java.util.List;

public class CallInst extends Instruction {
    String functionIdentifier;

    public CallInst(FunctionSymbol function, List<Value> arguments, BasicBlock curBlock) {
        super(function.getIdentifier() + "_call", function.getType(), curBlock);
        functionIdentifier = function.IRName();
        for (Value v : arguments) {
            addOperand(v);
        }
    }

    //specially for utility functions
    public CallInst(String identifier, Type type, List<Value> arguments, BasicBlock curBlock) {
        super(identifier + "_call", type, curBlock);
        functionIdentifier = identifier;
        for (Value v : arguments) {
            addOperand(v);
        }
    }
}
