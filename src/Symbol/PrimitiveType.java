package Symbol;

public class PrimitiveType extends Type{

    public PrimitiveType(String identifier) {
        super(identifier);
    }

    @Override
    public boolean isInt() {
        return getIdentifier().equals("int");
    }

    @Override
    public boolean isBoolean() {
        return getIdentifier().equals("bool");
    }
}