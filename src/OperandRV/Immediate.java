package OperandRV;

public class Immediate extends OperandRV {
    private int value;
    private boolean stack, topDown;

    public Immediate(int value) {
        this.value = value;
        this.stack = false;
    }

    public Immediate(int value, boolean topDown) {
        this.value = value;
        this.stack = true;
        this.topDown = topDown;
    }


}
