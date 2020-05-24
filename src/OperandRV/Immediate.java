package OperandRV;

public class Immediate extends OperandRV {
    private int value;
    private boolean stack, downward;

    public Immediate(){}

    public Immediate(int value) {
        this.value = value;
        this.stack = false;
    }

    public Immediate(int value, boolean downward) {
        this.value = value;
        this.stack = true;
        this.downward = downward;
    }


}
