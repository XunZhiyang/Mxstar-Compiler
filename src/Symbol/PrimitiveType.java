package Symbol;

public class PrimitiveType extends Type{

    public PrimitiveType(String identifier) {
        super(identifier);
    }

    public PrimitiveType(String identifier, int bitLen) {
        super(identifier, bitLen);
    }

    @Override
    public boolean isInt() {
        return getIdentifier().equals("int");
    }

    @Override
    public boolean isBoolean() {
        return getIdentifier().equals("bool");
    }

    @Override
    public boolean isVoid() {
        return getIdentifier().equals("void");
    }

    @Override
    public String IRName() {
        switch (getIdentifier()) {
            case "int":
                return "i32";
            case "bool":
                return "i1";
            case "_char":
                return "i8";
            case "void":
                return "void";
            default:
                throw new RuntimeException();
        }
    }
}