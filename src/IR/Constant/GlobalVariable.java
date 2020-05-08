package IR.Constant;

import IR.IRVisitor;
import Symbol.Type;

public class GlobalVariable extends Constant {
    public GlobalVariable(String identifier, Type type) {
        super(identifier, type.derivesFromClass() ? type.getPointer() : type);
    }

    @Override
    public String getIdentifier() {
        return "@" + identifier;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
