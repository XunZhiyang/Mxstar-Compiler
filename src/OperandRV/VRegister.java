package OperandRV;

import IR.Constant.BoolConst;
import IR.Value;

public class VRegister extends Register{
    private boolean global = false;

    public VRegister(int identifier, int byteNum) {

    }

    public VRegister(int identifier) {  //default 4 bytes

    }

    public VRegister(String identifier, int byteNum) {
        global = true;
    }

    @Override
    public boolean isGlobal() {
        return global;
    }
}
