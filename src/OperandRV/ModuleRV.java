package OperandRV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import IR.Constant.Function;
import IR.Module;

public class ModuleRV {
    private List<FunctionRV> functions = new ArrayList<>();
    private List<VRegister> globalVariables = new ArrayList<>();
    private Map<VRegister, String> stringConst = new HashMap<>();

    public ModuleRV() {

    }

    public void addGlobalVariable(VRegister register) {
        globalVariables.add(register);
    }

    public void addStringConst(VRegister register, String string) {
        stringConst.put(register, string);
    }

    public List<FunctionRV> getFunctions() {
        return functions;
    }

    public List<VRegister> getGlobalVariables() {
        return globalVariables;
    }
}
