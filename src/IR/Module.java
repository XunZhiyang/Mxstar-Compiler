package IR;

import IR.Constant.Function;
import Symbol.ClassType;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private List<ClassType> classList = new ArrayList<>();
    private List<Function> functionList = new ArrayList<>();
    private List<Value> globalVariableList = new ArrayList<>();

    public void addClass(ClassType c) {
        classList.add(c);
    }

    public void addFunction(Function f) {
        functionList.add(f);
    }

    public void addGlobalVariable(Value v) {
        globalVariableList.add(v);
    }
}
