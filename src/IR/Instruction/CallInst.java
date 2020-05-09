package IR.Instruction;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.IRVisitor;
import IR.Value;
import Symbol.FunctionSymbol;
import Symbol.GlobalScope;
import Symbol.Type;

import java.util.List;

public class CallInst extends Instruction {
    String functionIdentifier;

    public CallInst(FunctionSymbol function, List<Value> arguments, BasicBlock curBlock) {
        super(function.getIdentifier() + "_call", function.getType() == null ? GlobalScope.getVoidType() :
                (function.getType().derivesFromClass() ? function.getType().getPointer() : function.getType()), curBlock);
        functionIdentifier = function.IRName();
        for (Value v : arguments) {
            addOperand(v);
        }
    }

    //specially for utility functions
    public CallInst(String identifier, Type type, List<Value> arguments, BasicBlock curBlock) {
        super(identifier + "_call", type, curBlock);
        functionIdentifier = "@" + identifier;
        for (Value v : arguments) {
            addOperand(v);
        }
    }

    public String getFunctionIdentifier() {
        return functionIdentifier;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
