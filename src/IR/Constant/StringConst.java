package IR.Constant;

import IR.IRVisitor;
import Symbol.ArrayType;
import Symbol.ClassType;
import Symbol.GlobalScope;

public class StringConst extends GlobalVariable {
    String string;
    public StringConst(String string, int length) {
        super(".stringConst", (new ArrayType(GlobalScope.getCharType(), length + 1)).getPointer());
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
