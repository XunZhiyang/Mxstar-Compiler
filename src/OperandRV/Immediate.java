package OperandRV;

public class Immediate extends OperandRV {
    private int value;
    private boolean toBeAdjusted, downward;

    public Immediate(){}

    public Immediate(int value) {
        this.value = value;
        this.toBeAdjusted = false;
    }

    public Immediate(int value, boolean downward) {
        this.value = value;
        this.toBeAdjusted = true;
        this.downward = downward;
    }

    public void adjust(int offset) {
        if (this.toBeAdjusted) {
            toBeAdjusted = false;
            value += (downward ? -1 : 1) * offset;
        }
    }

}
