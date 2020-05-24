package IR.Constant;

import IR.IRVisitor;
import Symbol.GlobalScope;

public class BoolConst extends Constant {
    private boolean value;

    public BoolConst(boolean value) {
        super("_const", GlobalScope.getBoolType());
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String getIdentifier() {
        return value ? "1" : "0";
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
