package OperandRV;

public class Register extends OperandRV {
    public Register colour;

    public Register(String identifier) {
        super(identifier);
    }

    public Register(){}

    public boolean isGlobal() { return false; }

    @Override
    public String getIdentifier() {
        if (colour != null)
            return colour.identifier;
        return identifier;
    }

}
