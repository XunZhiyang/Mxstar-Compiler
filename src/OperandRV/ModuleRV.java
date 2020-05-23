package OperandRV;

import java.util.ArrayList;
import java.util.List;

import IR.Constant.Function;
import IR.Module;

public class ModuleRV {
    private List<FunctionRV> functions = new ArrayList<>();

    public ModuleRV() {
    }

    public List<FunctionRV> getFunctions() {
        return functions;
    }
}
