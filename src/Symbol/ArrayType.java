package Symbol;

//only for IR, normal arrays are "PointerType"
public class ArrayType extends Type{
    Type baseType;
    int dim;

    public ArrayType(Type baseType, int dim) {
        super("_arrayType");
        this.baseType = baseType;
        this.dim = dim;
        this.bitLen = baseType.bitLen;
    }

    @Override
    public String IRName() {
        return "[" + dim + " x " + baseType.IRName() + "]";
    }
}
