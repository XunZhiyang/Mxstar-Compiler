package Symbol;

import Utils.Position;
import Utils.SemanticError;

public abstract class Type {
    private String identifier;
    private int bitLen;

    public Type(String identifier) {
        this.identifier = identifier;
    }

    public Type(String identifier, int bitLen) {
        this.identifier = identifier;
        this.bitLen = bitLen;
    }

    public int getBitLen() {
        return bitLen;
    }

    public Type getPointer() {  //will be overridden by PointerType
        return new PointerType(this, 1);
    }

    public void setBitLen(int bitLen) {
        this.bitLen = bitLen;
    }

    public String getIdentifier() {
        return identifier;
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

    public boolean isPointer() {
        return false;
    }

    public boolean isVoid() {
        return false;
    }
}

