package IR.Constant;

import IR.IRVisitor;
import Symbol.GlobalScope;
import Symbol.Type;

import java.util.Map;

public class IntConst extends Constant {
    int value;

    public IntConst(int value) {
        super("_const", GlobalScope.getIntType());
        this.value = value;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
