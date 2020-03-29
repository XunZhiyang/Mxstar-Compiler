package Symbol;

import Utils.Position;
import Utils.SemanticError;

public abstract class Type {
    private String typeName;

    public Type(String typename) {
        this.typeName = typename;
    }

    public String getName() {
        return typeName;
    }

    public void compatible(Type type, Position position) {
        if (!typeName.equals(type.getName())) {
            throw new SemanticError("Type not compatible.", position);
        }
    }
}

