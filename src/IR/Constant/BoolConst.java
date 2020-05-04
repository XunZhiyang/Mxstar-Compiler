package IR.Constant;

import Symbol.GlobalScope;

public class BoolConst extends Constant {
    boolean value;

    public BoolConst(boolean value) {
        super("_const", GlobalScope.getBoolType());
        this.value = value;
    }
}
