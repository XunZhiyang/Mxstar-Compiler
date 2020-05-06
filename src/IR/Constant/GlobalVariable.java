package IR.Constant;

import IR.IRVisitor;
import Symbol.Type;

public class GlobalVariable extends Constant {
    public GlobalVariable(String identifier, Type type) {
        super(identifier, type);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
