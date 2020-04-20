package IR;

import IR.Constant.Function;
import Symbol.ClassType;

import java.util.ArrayList;
import java.util.List;

public class Module {
    List<ClassType> classList = new ArrayList<>();
    List<Function> functionList = new ArrayList<>();
}
