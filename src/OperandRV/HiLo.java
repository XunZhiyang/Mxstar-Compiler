package OperandRV;

public class HiLo extends Immediate {
    Register reg;
    boolean high;

    public HiLo(Register reg, boolean high) {
        this.reg = reg;
        this.high = high;
    }

    @Override
    public String toString() {
        return "%" + (high ? "hi" : "lo") + "(" + reg.getIdentifier() + ")";
    }

}
