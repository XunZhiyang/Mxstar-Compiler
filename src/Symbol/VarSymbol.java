package Symbol;

import AST.FuncDeclNode;

public class VarSymbol extends Symbol {
    public VarSymbol(Type type, String identifier, FuncDeclNode define) {
        super(type, identifier, define);
    }
}
