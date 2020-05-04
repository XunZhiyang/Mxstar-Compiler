package IR.Constant;

import Symbol.GlobalScope;
import Symbol.PointerType;

public class NullConst extends Constant {
    public NullConst() {
        super("_const", GlobalScope.getNullType());
    }
}
