package Symbol;

import AST.FuncDeclNode;

public class FunctionSymbol extends Symbol implements Scope {
    private Scope fatherScope;

    public FunctionSymbol(Type type, String identifier, FuncDeclNode define, Scope fatherScope) {
        super(type, identifier, define);
        this.fatherScope = fatherScope;
    }


}
