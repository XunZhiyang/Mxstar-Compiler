package OperandRV;

public abstract class OperandRV {
    protected String identifier;

    public OperandRV(){}

    public OperandRV(String identifier) {
        this.identifier = identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
