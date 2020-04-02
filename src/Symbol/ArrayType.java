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

    public Type getMember() {
        if (dim == 1) return baseType;
        else return new ArrayType(baseType, dim - 1);
    }

    @Override
    public boolean isArray() {
        return true;
    }
}
