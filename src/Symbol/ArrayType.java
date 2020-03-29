package Symbol;

public class ArrayType extends Type{
    private Type baseType;
    private int dim;

    public ArrayType(Type baseType, int dim) {
        super(baseType.getName() + "+" + String.valueOf(dim));
        this.baseType = baseType;
        this.dim = dim;
    }
}
