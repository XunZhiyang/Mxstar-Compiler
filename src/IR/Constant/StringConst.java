package IR.Constant;

import Symbol.ArrayType;
import Symbol.ClassType;
import Symbol.GlobalScope;

public class StringConst extends GlobalVariable {
    public StringConst(String string) {
        super(".stringConst", (new ArrayType(GlobalScope.getCharType(), string.length() + 1)).getPointer());
    }
}
