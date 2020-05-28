package OperandRV;

public class Register extends OperandRV {

    public Register(String identifier) {
        super(identifier);
    }

    public Register(){}

    public boolean isGlobal() { return false; }

}
