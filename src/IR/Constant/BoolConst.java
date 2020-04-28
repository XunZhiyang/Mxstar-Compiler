package IR.Constant;

import Symbol.GlobalScope;

public class BoolConst extends Constant {
    boolean value;

    public BoolConst(boolean value) {
        super("_const", GlobalScope.boolType);
        this.value = value;
    }
}
