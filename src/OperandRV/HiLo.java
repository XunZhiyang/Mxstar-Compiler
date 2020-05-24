package OperandRV;

public class HiLo extends Immediate {
    Register reg;
    boolean high;

    public HiLo(Register reg, boolean high) {
        this.reg = reg;
        this.high = high;
    }

}
