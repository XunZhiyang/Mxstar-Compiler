package IR.Constant;

import IR.BasicBlock;
import IR.IRVisitor;
import Symbol.GlobalScope;
import Symbol.Type;

import java.util.ArrayList;
import java.util.List;

public class Function extends Constant {
    List<BasicBlock> basicBlockList = new ArrayList<>();

    public Function(String name, Type type) {
        super(name, type == null ? GlobalScope.getVoidType() : (type.derivesFromClass() ? type.getPointer() : type));
    }

    public BasicBlock add(String identifier) {
        BasicBlock newBlock = new BasicBlock(identifier);
        basicBlockList.add(newBlock);
        return newBlock;
    }

    public List<BasicBlock> getBasicBlockList() {
        return basicBlockList;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
