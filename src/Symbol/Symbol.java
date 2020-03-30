package Symbol;

import AST.ASTNode;
import Utils.Position;

public abstract class Symbol {
    private Type type;
    private String identifier;
    private Scope scope;
    private ASTNode define;

    public Symbol(Type type, String identifier, ASTNode define) {
        this.type = type;
        this.identifier = identifier;
        this.define = define;
    }

    public Type getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Position getPos() {
        return define.getPosition();
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean ifConstructor() {
        return false;
    }

    public Position getPosition() {
        return define.getPosition();
    }
}
