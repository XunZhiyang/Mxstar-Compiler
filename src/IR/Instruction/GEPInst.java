package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.ClassType;
import Symbol.Type;

public class GEPInst extends Instruction {
    public GEPInst(Value classObject, Type fieldType, BasicBlock curBlock) {
        super(".GEP", fieldType, curBlock);
        addOperand(classObject);
//        addOperand(index);
    }

    void addIndex(Value... index) {
        addOperand(index);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
