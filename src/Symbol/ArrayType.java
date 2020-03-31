package Symbol;

public class ArrayType extends Type{
    private Type baseType;
    private int dim;

    public ArrayType(Type baseType, int dim) {
        super(baseType.getIdentifier() + "+" + dim);
        this.baseType = baseType;
        this.dim = dim;
    }

    @Override
    public boolean isNullable() {
        return true;
    }

    @Override
    public boolean isArray() {
        return true;
    }
}
