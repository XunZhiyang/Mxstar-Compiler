package Symbol;

import Utils.Position;
import Utils.SemanticError;

public abstract class Type {
    private String identifier;

    public Type(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void compatible(Type type, Position position) {
        if (!identifier.equals(type.getIdentifier())) {
            throw new SemanticError("Type not compatible.", position);
        }
    }

    public boolean equals(Type type) {
        return identifier.equals(type.getIdentifier());
    }

    public boolean assignable(Type rhs) {
        return this.equals(rhs) || (this.isNullable() && rhs.isNull());
    }

    public boolean isInt() {
        return false;
    }

    public boolean isBoolean() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isNullable() {
        return false;
    }

    public boolean isClass() {
        return false;
    }

    public boolean isArray() {
        return false;
    }
}

