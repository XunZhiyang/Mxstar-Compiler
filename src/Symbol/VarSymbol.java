package Symbol;

import AST.ASTNode;
import IR.Value;

public class VarSymbol extends Symbol {
    private Value value;

    public VarSymbol(Type type, String identifier, ASTNode define) {
        super(type, identifier, define);
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }
}
