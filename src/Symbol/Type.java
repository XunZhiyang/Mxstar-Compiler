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
}

