package OperandRV.InstRV;

import Backend.RVPrinter;
import OperandRV.BlockRV;
import OperandRV.FunctionRV;
import OperandRV.RV32;
import OperandRV.Register;

import java.util.ArrayList;
import java.util.List;

public class CallInst extends InstRV {
    private String function;

    public CallInst(String function, BlockRV curBlock) {
        super(curBlock);
        this.function = function.substring(1);
    }

    public String getFunction() {
        return function;
    }

    @Override
    public void print(RVPrinter printer) {
        printer.print(this);
    }

    public List<Register> getUses() {
        List<Register> ret = new ArrayList<>();
//        RV32.calleeSave.forEach(reg -> ret.add(RV32.get(reg)));
        return ret;
    }

    public List<Register> getDef() {
        List<Register> ret = new ArrayList<>();
        RV32.callerSave.forEach(reg -> ret.add(RV32.get(reg)));
        return ret;
    }
}