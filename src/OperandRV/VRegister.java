package OperandRV;

import IR.Constant.BoolConst;
import IR.Value;

public class VRegister extends Register{
    private boolean global = false;
    private int byteNum;

    public VRegister(int identifier, int byteNum) {
        super("v" + identifier);
        this.byteNum = byteNum;
    }

    public VRegister(int identifier) {  //default 4 bytes
        super("v" + identifier);
        this.byteNum = 4;
    }

    public VRegister() {  //default 4 bytes
        this.byteNum = 4;
    }

    public VRegister(String identifier, int byteNum) {
        super("g_" + identifier.substring(1));
        global = true;
        this.byteNum = byteNum;
    }

    @Override
    public boolean isGlobal() {
        return global;
    }

    public int getByteNum() {
        return byteNum;
    }
}
