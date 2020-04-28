package AST;

import IR.Value;
import Symbol.FunctionSymbol;
import Symbol.Type;
import Utils.Position;

public abstract class Expr extends ASTNode {
    private Type type;
    private boolean isLvalue;
    private FunctionSymbol functionSymbol;

    private Value value;

    public Expr(Position position) {
        super(position);
        isLvalue = false;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Value getValue(Value value) {
        return value;
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
        return functionSymbol != null;
    }

    public Value getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }
}
