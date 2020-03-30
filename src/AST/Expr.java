package AST;

import Symbol.Type;
import Utils.Position;

public abstract class Expr extends ASTNode {
    Type type;
    private boolean isLvalue;
    private boolean isCallable;

    public Expr(Position position) {
        super(position);
        isCallable = false;
        isLvalue = false;
    }

    public void setLvalue(boolean isLvalue) {
        this.isLvalue = isLvalue;
    }

    public void setCallable(boolean isCallable) {
        this.isCallable = isCallable;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean getLvalue() {
        return isLvalue;
    }

    public boolean getCallable() {
        return isCallable;
    }

    public Type getType() {
        return type;
    }
}
