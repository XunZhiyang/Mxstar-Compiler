package Symbol;

import AST.ASTNode;

public class VarSymbol extends Symbol {
    public VarSymbol(Type type, String identifier, ASTNode define) {
        super(type, identifier, define);
    }
}
