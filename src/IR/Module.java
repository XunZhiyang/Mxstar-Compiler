package IR;

import IR.Constant.Function;
import Symbol.ClassType;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private List<ClassType> classList = new ArrayList<>();
    private List<Function> functionList = new ArrayList<>();
    private List<Value> globalVariableList = new ArrayList<>();

    public List<ClassType> getClassList() {
        return classList;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public List<Value> getGlobalVariableList() {
        return globalVariableList;
    }

    public void addClass(ClassType c) {
        classList.add(c);
    }

    public void addFunction(Function f) {
        functionList.add(f);
    }

    public void addGlobalVariable(Value v) {
        globalVariableList.add(v);
    }

    public Function getFunction(String identifier) {
        for (Function function : functionList) {
            if (function.getIdentifier().equals(identifier)) return function;
        }
        throw new RuntimeException();
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
