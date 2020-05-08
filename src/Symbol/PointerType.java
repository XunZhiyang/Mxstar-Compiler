package Symbol;

import java.util.Collections;

public class PointerType extends Type{
    private Type baseType;
    private int dim;

    public PointerType(Type baseType, int dim) {
        super(baseType.getIdentifier() + "+" + dim, 64);
        this.baseType = baseType;
        this.dim = dim;
    }

    @Override
    public Type getPointer() {
        return new PointerType(baseType, dim + 1);
    }

    public Type getMember() {
        if (dim == 1) return baseType;
        else return new PointerType(baseType, dim - 1);
    }

    public Type getBaseType() {
        return baseType;
    }

    @Override
    public boolean isNullable() {
        return true;
    }

    @Override
    public boolean isPointer() {
        return true;
    }

    @Override
    public boolean isString() {
        return getIdentifier().equals("_char+1");
    }

    @Override
    public String IRName() {
        return baseType.IRName() + String.join("", Collections.nCopies(dim, "*"));
//        return baseType.IRName() + repeatString("*", dim, "");
    }
}
