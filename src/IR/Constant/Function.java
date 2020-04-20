package IR.Constant;

import IR.BasicBlock;
import Symbol.Type;

import java.util.ArrayList;
import java.util.List;

public class Function extends Constant {
    List<BasicBlock> basicBlockList = new ArrayList<>();

    public Function(String name, Type type) {
        super(name, type);
    }
}
