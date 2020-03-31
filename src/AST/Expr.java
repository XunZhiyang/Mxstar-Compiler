package AST;

import Symbol.FunctionSymbol;
import Symbol.Type;
import Utils.Position;

public abstract class Expr extends ASTNode {
    Type type;
    private boolean isLvalue;
    private FunctionSymbol functionSymbol;

    public Expr(Position position) {
        super(position);
        isLvalue = false;
    }

    public void setLvalue(boolean isLvalue) {
        this.isLvalue = isLvalue;
    }

    public void setFunctionSymbol(FunctionSymbol functionSymbol) {
        this.functionSymbol = functionSymbol;
    }

    public FunctionSymbol getFunctionSymbol() {
        return functionSymbol;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean getLvalue() {
        return isLvalue;
    }

    public boolean getCallable() {
        return functionSymbol == null;
    }

    public Type getType() {
        return type;
    }
}
