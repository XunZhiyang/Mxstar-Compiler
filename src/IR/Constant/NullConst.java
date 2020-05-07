package IR.Constant;

import IR.IRVisitor;
import Symbol.GlobalScope;
import Symbol.PointerType;

public class NullConst extends Constant {
    public NullConst() {
        super("_const", GlobalScope.getNullType());
    }

    @Override
    public String getIdentifier() {
        return "null";
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
